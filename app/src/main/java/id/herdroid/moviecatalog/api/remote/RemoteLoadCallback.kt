package id.herdroid.moviecatalog.api.remote

import id.herdroid.moviecatalog.data.response.MovieItem
import id.herdroid.moviecatalog.data.response.TvShowItem

interface RemoteLoadCallback {

    interface LoadMoviesCallback {
        fun onAllMoviesReceived(movie: List<MovieItem>?)
        fun onAllMoviesNotReceived(message : String)
    }


    interface LoadDetailMovieCallback {
        fun onDetailMovieReceived(responseMovie : MovieItem?)
        fun onDetailMovieNotReceived(message : String)
    }

    interface LoadTvShowsCallback {
        fun onAllTvShowsReceived(tvShow: List<TvShowItem>?)
        fun onAllTvShowsNotReceived(message : String)
    }

    interface LoadDetailTvShowCallback {
        fun onDetailTvShowsReceived(responseTvShow : TvShowItem?)
        fun onDetailTvShowsNotReceived(message : String)
    }
}