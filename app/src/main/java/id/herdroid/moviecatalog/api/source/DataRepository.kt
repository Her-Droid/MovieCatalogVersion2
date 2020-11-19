package id.herdroid.moviecatalog.api.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.herdroid.moviecatalog.api.remote.RemoteDataRepository
import id.herdroid.moviecatalog.api.remote.RemoteLoadCallback
import id.herdroid.moviecatalog.data.response.MovieItem
import id.herdroid.moviecatalog.data.response.TvShowItem

class DataRepository(private val remoteDataRepository: RemoteDataRepository) : DataSource {

    companion object {
        @Volatile
        private var instance: DataRepository? = null

        fun getInstance(remoteDataRepository: RemoteDataRepository): DataRepository =
            instance ?: synchronized(this) {
                instance ?: DataRepository(remoteDataRepository)
            }
    }

    override fun getListMovie(page: Int): LiveData<List<MovieItem>> {
        val listMovie = MutableLiveData<List<MovieItem>>()
        remoteDataRepository.getMovies(object : RemoteLoadCallback.LoadMoviesCallback {
            override fun onAllMoviesReceived(movie: List<MovieItem>?) {
                listMovie.postValue(movie)
            }

            override fun onAllMoviesNotReceived(message: String) {

            }
        })
        return listMovie
    }

    override fun getMovieById(movieId: Int): LiveData<MovieItem> {
        val detailMovie = MutableLiveData<MovieItem>()
        remoteDataRepository.getDetailMovie(
            movieId,
            object : RemoteLoadCallback.LoadDetailMovieCallback {
                override fun onDetailMovieReceived(responseMovie: MovieItem?) {
                    detailMovie.postValue(responseMovie)
                }

                override fun onDetailMovieNotReceived(message: String) {

                }
            })
        return detailMovie
    }

    override fun getListTvShow(page: Int): LiveData<List<TvShowItem>> {
        val listTvShow = MutableLiveData<List<TvShowItem>>()
        remoteDataRepository.getTvShows(
            object : RemoteLoadCallback.LoadTvShowsCallback {
                override fun onAllTvShowsReceived(tvShow: List<TvShowItem>?) {
                    listTvShow.postValue(tvShow)
                }

                override fun onAllTvShowsNotReceived(message: String) {

                }
            })
        return listTvShow
    }

    override fun getTvShowById(tvShowId: Int): LiveData<TvShowItem> {
        val detailTvShow = MutableLiveData<TvShowItem>()
        remoteDataRepository.getDetailTvShow(tvShowId,
        object : RemoteLoadCallback.LoadDetailTvShowCallback{
            override fun onDetailTvShowsReceived(responseTvShow: TvShowItem?) {
                detailTvShow.postValue(responseTvShow)
            }

            override fun onDetailTvShowsNotReceived(message: String) {

            }
        })
        return detailTvShow
    }


}