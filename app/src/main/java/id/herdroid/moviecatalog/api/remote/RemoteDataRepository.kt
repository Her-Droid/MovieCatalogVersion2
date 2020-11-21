package id.herdroid.moviecatalog.api.remote


import id.herdroid.moviecatalog.api.ApiService
import id.herdroid.moviecatalog.data.entity.MovieEntity
import id.herdroid.moviecatalog.data.entity.TvShowEntity
import id.herdroid.moviecatalog.data.response.MovieResponse
import id.herdroid.moviecatalog.data.response.TvShowResponse
import id.herdroid.moviecatalog.utils.EspressoIdlingResource
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class RemoteDataRepository private constructor(private val apiService: ApiService) {


    companion object {
        @Volatile
        private var instance: RemoteDataRepository? = null


        fun getInstance(apiService: ApiService): RemoteDataRepository =
            instance ?: synchronized(this) {
                instance ?: RemoteDataRepository(apiService)

            }
    }


    fun getMovies(loadMoviesCallback: RemoteLoadCallback.LoadMoviesCallback) {
        EspressoIdlingResource.increment()
        val getApiService = apiService.getMovies("28140e5b657db765f7b67c7ced117502")
        getApiService.subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<MovieResponse> {
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable?) {

                }

                override fun onNext(value: MovieResponse?) {
                    loadMoviesCallback.onAllMoviesReceived(value?.results)
                    EspressoIdlingResource.decrement()
                }

                override fun onError(e: Throwable?) {
                    loadMoviesCallback.onAllMoviesNotReceived(e?.message.toString())
                }

            },)
    }

    fun getDetailMovie(movieId: Int, detailMovieCallback: RemoteLoadCallback.LoadDetailMovieCallback){
        EspressoIdlingResource.increment()
        val getApiService = apiService.getMovieById(movieId,"28140e5b657db765f7b67c7ced117502")
        getApiService.subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<MovieEntity>{
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable?) {

                }

                override fun onNext(value: MovieEntity?) {
                    detailMovieCallback.onDetailMovieReceived(value)
                    EspressoIdlingResource.decrement()
                }

                override fun onError(e: Throwable?) {
                    detailMovieCallback.onDetailMovieNotReceived(e?.message.toString())
                }
            })
    }

    fun getTvShows(loadTvShowsCallback: RemoteLoadCallback.LoadTvShowsCallback){
        EspressoIdlingResource.increment()
        val getApiService = apiService.getTvShows("28140e5b657db765f7b67c7ced117502")
        getApiService.subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<TvShowResponse>{
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable?) {

                }

                override fun onNext(value: TvShowResponse?) {
                    loadTvShowsCallback.onAllTvShowsReceived(value?.results)
                    EspressoIdlingResource.decrement()
                }

                override fun onError(e: Throwable?) {
                    loadTvShowsCallback.onAllTvShowsNotReceived(e?.message.toString())
                }
            })
    }



    fun getDetailTvShow(tvShowId: Int, detailTvShowCallback: RemoteLoadCallback.LoadDetailTvShowCallback){
        EspressoIdlingResource.increment()
        val getApiService = apiService.getTvShowById(tvShowId, "28140e5b657db765f7b67c7ced117502")
        getApiService.subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object  : Observer<TvShowEntity>{
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable?) {

                }

                override fun onNext(value: TvShowEntity?) {
                    detailTvShowCallback.onDetailTvShowsReceived(value)
                    EspressoIdlingResource.decrement()
                }

                override fun onError(e: Throwable?) {
                    detailTvShowCallback.onDetailTvShowsNotReceived(e?.message.toString())
                }
            })
    }
}