package id.herdroid.moviecatalog.api.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import id.herdroid.moviecatalog.data.entity.MovieEntity
import id.herdroid.moviecatalog.data.entity.TvShowEntity
import id.herdroid.moviecatalog.vo.Resource

interface DataSource {

    fun getAllMovies(): LiveData<Resource<PagedList<MovieEntity>>>

    fun getAllTvShows(): LiveData<Resource<PagedList<TvShowEntity>>>

    fun getDetailMovie(id: String): LiveData<Resource<MovieEntity>>

    fun getDetailTvShow(id: String): LiveData<Resource<TvShowEntity>>

    fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>>

    fun getFavoriteTvShows(): LiveData<PagedList<TvShowEntity>>

    fun getSortedMovies(sort: String): LiveData<PagedList<MovieEntity>>

    fun getSortedTvShows(sort: String): LiveData<PagedList<TvShowEntity>>

    fun setFavoriteMovie(movie: MovieEntity, state: Boolean)

    fun setFavoriteTvShow(tvShow: TvShowEntity, state: Boolean)

}