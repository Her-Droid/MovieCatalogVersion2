package id.herdroid.moviecatalog.api.source

import androidx.lifecycle.LiveData
import id.herdroid.moviecatalog.data.response.MovieItem
import id.herdroid.moviecatalog.data.response.TvShowItem

interface DataSource {

    fun getListMovie(page: Int): LiveData<List<MovieItem>>

    fun getMovieById(movieId: Int): LiveData<MovieItem>

    fun getListTvShow(page: Int): LiveData<List<TvShowItem>>

    fun getTvShowById(tvShowId: Int): LiveData<TvShowItem>
}