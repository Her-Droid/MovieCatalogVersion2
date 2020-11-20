package id.herdroid.moviecatalog.api.remote

import id.herdroid.moviecatalog.data.entity.MovieEntity
import id.herdroid.moviecatalog.data.entity.TvShowEntity

interface RemoteLoadCallback {

    interface LoadMoviesCallback {
        fun onAllMoviesReceived(movie: List<MovieEntity>?)
        fun onAllMoviesNotReceived(message : String)
    }


    interface LoadDetailMovieCallback {
        fun onDetailMovieReceived(responseMovie : MovieEntity?)
        fun onDetailMovieNotReceived(message : String)
    }

    interface LoadTvShowsCallback {
        fun onAllTvShowsReceived(tvShow: List<TvShowEntity>?)
        fun onAllTvShowsNotReceived(message : String)
    }

    interface LoadDetailTvShowCallback {
        fun onDetailTvShowsReceived(responseTvShow : TvShowEntity?)
        fun onDetailTvShowsNotReceived(message : String)
    }
}