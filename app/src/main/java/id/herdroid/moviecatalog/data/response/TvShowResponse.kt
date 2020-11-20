package id.herdroid.moviecatalog.data.response

import com.google.gson.annotations.SerializedName
import id.herdroid.moviecatalog.data.entity.TvShowEntity

data class TvShowResponse(
        @SerializedName("results")
        val results: List<TvShowEntity>
)


