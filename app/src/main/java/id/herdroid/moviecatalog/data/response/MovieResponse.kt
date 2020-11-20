package id.herdroid.moviecatalog.data.response

import com.google.gson.annotations.SerializedName
import id.herdroid.moviecatalog.data.entity.MovieEntity

data class MovieResponse(
        @SerializedName("results")
        val results: List<MovieEntity>
)


