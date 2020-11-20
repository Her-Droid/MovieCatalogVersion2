package id.herdroid.moviecatalog.api.source

import androidx.lifecycle.LiveData
import id.herdroid.moviecatalog.data.entity.MovieEntity
import id.herdroid.moviecatalog.data.entity.TvShowEntity

interface DataSource {

    fun getListMovie(): LiveData<List<MovieEntity>>

    fun getMovieById(movieId: Int): LiveData<MovieEntity>

    fun getListTvShow(): LiveData<List<TvShowEntity>>

    fun getTvShowById(tvShowId: Int): LiveData<TvShowEntity>
}