package id.herdroid.moviecatalog.api.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.herdroid.moviecatalog.api.remote.RemoteDataRepository
import id.herdroid.moviecatalog.api.remote.RemoteLoadCallback
import id.herdroid.moviecatalog.data.entity.MovieEntity
import id.herdroid.moviecatalog.data.entity.TvShowEntity

class DataRepository(private val remoteDataRepository: RemoteDataRepository) : DataSource {

    companion object {
        @Volatile
        private var instance: DataRepository? = null

        fun getInstance(remoteDataRepository: RemoteDataRepository): DataRepository =
            instance ?: synchronized(this) {
                instance ?: DataRepository(remoteDataRepository)
            }
    }

    override fun getListMovie(): LiveData<List<MovieEntity>> {
        val listMovie = MutableLiveData<List<MovieEntity>>()
        remoteDataRepository.getMovies(object : RemoteLoadCallback.LoadMoviesCallback {
            override fun onAllMoviesReceived(movie: List<MovieEntity>?) {
                listMovie.postValue(movie)
            }

            override fun onAllMoviesNotReceived(message: String) {

            }
        })
        return listMovie
    }

    override fun getMovieById(movieId: Int): LiveData<MovieEntity> {
        val detailMovie = MutableLiveData<MovieEntity>()
        remoteDataRepository.getDetailMovie(movieId,
            object : RemoteLoadCallback.LoadDetailMovieCallback {
                override fun onDetailMovieReceived(responseMovie: MovieEntity?) {
                    detailMovie.postValue(responseMovie)
                }

                override fun onDetailMovieNotReceived(message: String) {

                }
            })
        return detailMovie
    }

    override fun getListTvShow(): LiveData<List<TvShowEntity>> {
        val listTvShow = MutableLiveData<List<TvShowEntity>>()
        remoteDataRepository.getTvShows(
            object : RemoteLoadCallback.LoadTvShowsCallback {
                override fun onAllTvShowsReceived(tvShow: List<TvShowEntity>?) {
                    listTvShow.postValue(tvShow)
                }

                override fun onAllTvShowsNotReceived(message: String) {

                }
            })
        return listTvShow
    }

    override fun getTvShowById(tvShowId: Int): LiveData<TvShowEntity> {
        val detailTvShow = MutableLiveData<TvShowEntity>()
        remoteDataRepository.getDetailTvShow(tvShowId,
        object : RemoteLoadCallback.LoadDetailTvShowCallback{
            override fun onDetailTvShowsReceived(responseTvShow: TvShowEntity?) {
                detailTvShow.postValue(responseTvShow)
            }

            override fun onDetailTvShowsNotReceived(message: String) {

            }
        })
        return detailTvShow
    }


}