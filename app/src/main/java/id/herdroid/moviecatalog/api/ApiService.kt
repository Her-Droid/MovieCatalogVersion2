package id.herdroid.moviecatalog.api

import id.herdroid.moviecatalog.data.response.MovieItem
import id.herdroid.moviecatalog.data.response.MovieResponse
import id.herdroid.moviecatalog.data.response.TvShowItem
import id.herdroid.moviecatalog.data.response.TvShowResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/top_rated")
    fun getMovies(
        @Query("api_key") apiKey : String
    ): Observable<MovieResponse>

    @GET("movie/{id}")
    fun getMovieById(
            @Path("id") movieId : Int?,
            @Query("api_key") apiKey : String
    ): Observable<MovieItem>

    @GET("tv/top_rated")
    fun getTvShows(
        @Query("api_key") apiKey : String
    ): Observable<TvShowResponse>

    @GET("tv/{id}")
    fun getTvShowById(
        @Path("id") tvId : Int?,
        @Query("api_key") apiKey: String
    ): Observable<TvShowItem>

}