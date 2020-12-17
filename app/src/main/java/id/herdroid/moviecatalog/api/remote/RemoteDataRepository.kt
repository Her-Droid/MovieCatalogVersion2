package id.herdroid.moviecatalog.api.remote


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.herdroid.moviecatalog.api.network.ApiClient
import id.herdroid.moviecatalog.api.network.ApiService
import id.herdroid.moviecatalog.data.entity.MovieEntity
import id.herdroid.moviecatalog.data.entity.TvShowEntity
import id.herdroid.moviecatalog.data.response.ListMovieResponse
import id.herdroid.moviecatalog.data.response.ListTvShowResponse
import id.herdroid.moviecatalog.data.response.MovieResponse
import id.herdroid.moviecatalog.data.response.TvShowResponse
import id.herdroid.moviecatalog.utils.Constants.API_KEY
import id.herdroid.moviecatalog.utils.EspressoIdlingResource
import id.herdroid.moviecatalog.vo.ApiResponse
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await
import java.io.IOException

class RemoteDataRepository private constructor(private val apiService: ApiService) {

    companion object {
        @Volatile
        private var instance: RemoteDataRepository? = null

        fun getInstance(apiService: ApiService): RemoteDataRepository =
                instance ?: synchronized(this) {
                    instance ?: RemoteDataRepository(apiService)
                }
    }

    fun getMovies(): MutableLiveData<ApiResponse<List<MovieEntity>?>> {
        EspressoIdlingResource.increment()
        val resultMovie = MutableLiveData<ApiResponse<List<MovieEntity>?>>()
        val call = apiService.getMovies(API_KEY)
        call.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<MovieResponse> {
                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(value: MovieResponse) {
                        resultMovie.value = ApiResponse.success(value.results)
                        EspressoIdlingResource.decrement()
                    }

                    override fun onError(e: Throwable) {

                    }

                })
        return resultMovie
    }

    fun getTvShows(): MutableLiveData<ApiResponse<List<TvShowEntity>?>> {
        EspressoIdlingResource.increment()
        val resultTvShow = MutableLiveData<ApiResponse<List<TvShowEntity>?>>()
        val call = apiService.getTvShows(API_KEY)
        call.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<TvShowResponse> {
                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(value: TvShowResponse) {
                        resultTvShow.value = ApiResponse.success(value.results)
                        EspressoIdlingResource.decrement()
                    }

                    override fun onError(e: Throwable) {

                    }

                })
        return resultTvShow
    }


}