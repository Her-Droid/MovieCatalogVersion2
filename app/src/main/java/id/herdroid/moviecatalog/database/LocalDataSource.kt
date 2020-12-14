package id.herdroid.moviecatalog.database

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.sqlite.db.SupportSQLiteQuery
import id.herdroid.moviecatalog.data.entity.MovieEntity
import id.herdroid.moviecatalog.data.entity.TvShowEntity
import id.herdroid.moviecatalog.database.dao.MovieDao
import id.herdroid.moviecatalog.database.dao.TvShowDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LocalDataSource private constructor(private val movieDao: MovieDao, private val tvShowDao: TvShowDao){

    companion object{
        private var INSTANCE: LocalDataSource? =null

        fun getInstance(movieDao: MovieDao, tvShowDao: TvShowDao): LocalDataSource{
            if (INSTANCE == null){
                INSTANCE = LocalDataSource(movieDao, tvShowDao)
            }
            return INSTANCE as LocalDataSource
        }
    }

    fun getMovieDb(): LiveData<List<MovieEntity>> = movieDao.getMovieDb()

    fun getDetailMovie(movieId : Int?): LiveData<MovieEntity> = movieDao.getMovieDbById(movieId)

    fun getFavoriteMovie(): DataSource.Factory<Int, MovieEntity> = movieDao.getFavoriteMovie()

    fun getSortedMovies(query: SupportSQLiteQuery): DataSource.Factory<Int, MovieEntity> = movieDao.getSortedMovies(query)

    fun insertMovie(movie: List<MovieEntity>){
      GlobalScope.launch(Dispatchers.Main) {
          movieDao.insertMovie(movie)
      }
    }

    fun setMovieFavorite(movie: MovieEntity, newState: Boolean?){
        movie.favorite = newState
        movieDao.updateMovie(movie)
    }

    fun removeFavoriteMovie(movie: MovieEntity) {
        GlobalScope.launch(Dispatchers.Main) {
            movieDao.deleteMovie(movie)
        }
    }



    fun getTvShowDb(): LiveData<List<TvShowEntity>> = tvShowDao.getTvShowDb()

    fun getDetailTvShow(tvShowId : Int?): LiveData<TvShowEntity> = tvShowDao.getTvShowDbById(tvShowId)

    fun getFavoriteTvShow(): DataSource.Factory<Int, TvShowEntity> = tvShowDao.getFavoriteTvShow()

    fun getSortedTvShows(query: SupportSQLiteQuery): DataSource.Factory<Int, TvShowEntity> = tvShowDao.getSortedTvShows(query)


    fun insertTvShow(tvShow: List<TvShowEntity>){
        GlobalScope.launch(Dispatchers.Main) {
            tvShowDao.insertTvShow(tvShow)
        }
    }

    fun setTvShowFavorite(tvShow: TvShowEntity, newState: Boolean?){
        tvShow.favorite = newState
        tvShowDao.updateTvShow(tvShow)
    }

    fun removeFavoriteTvShow(tvShow: TvShowEntity) {
        GlobalScope.launch(Dispatchers.Main) {
            tvShowDao.deleteTvShow(tvShow)
        }
    }


}