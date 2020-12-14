package id.herdroid.moviecatalog.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import id.herdroid.moviecatalog.utils.AppExecutors
import id.herdroid.moviecatalog.vo.ApiResponse
import id.herdroid.moviecatalog.vo.Resource
import id.herdroid.moviecatalog.vo.Status

abstract class NetworkBoundResource<ResultType, RequestType>(private val executors: AppExecutors) {

    private val result = MediatorLiveData<Resource<ResultType>>()

    private fun onFetchFailed() {}

    protected abstract fun loadDataFromDB(): LiveData<ResultType>

    protected abstract fun shouldFetch(data: ResultType): Boolean?

    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>?

    protected abstract fun saveCallResult(data: RequestType)

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        val apiResponse = createCall()

        result.addSource(dbSource) { newData ->
            result.setValue(Resource.loading(newData))
        }
        result.addSource(apiResponse!!) { response ->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)

            when (response.status) {
                Status.SUCCESS -> executors.diskIO().execute {

                    response.body?.let { saveCallResult(it) }

                    executors.mainThread().execute {
                        result.addSource(loadDataFromDB()
                        ) { newData -> result.setValue(Resource.success(newData)) }
                    }

                }

                Status.LOADING -> executors.mainThread().execute {
                    result.addSource(loadDataFromDB()
                    ) { newData -> result.setValue(Resource.success(newData)) }
                }
                Status.ERROR -> {
                    onFetchFailed()
                    result.addSource(
                            dbSource
                    ) { newData -> result.setValue(response.message?.let { Resource.error(it, newData) }) }
                }
            }
        }
    }

    fun asLiveData(): LiveData<Resource<ResultType>> {
        return result
    }

    init {
        result.value = Resource.loading(null)

        val dbSource = loadDataFromDB()

        result.addSource(dbSource) { data ->
            result.removeSource(dbSource)
            if (shouldFetch(data)!!) fetchFromNetwork(dbSource)
            else result.addSource(dbSource) { newData ->
                result.setValue(Resource.success(newData))
            }
        }
    }
}
