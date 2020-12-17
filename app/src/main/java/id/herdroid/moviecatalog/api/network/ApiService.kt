package id.herdroid.moviecatalog.api.network

import id.herdroid.moviecatalog.data.response.MovieResponse
import id.herdroid.moviecatalog.data.response.TvShowResponse
import id.herdroid.moviecatalog.utils.Constants.API_KEY
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/top_rated")
    fun getMovies(
        @Query("api_key") apiKey: String = API_KEY
    ): Observable<MovieResponse>


    @GET("tv/top_rated")
    fun getTvShows(
        @Query("api_key") apiKey: String = API_KEY
    ): Observable<TvShowResponse>



}