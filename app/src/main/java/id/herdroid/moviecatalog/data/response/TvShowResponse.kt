package id.herdroid.moviecatalog.data.response

import com.google.gson.annotations.SerializedName

data class TvShowResponse(
        @SerializedName("result")
        val results: List<TvShowItem>
)

data class TvShowItem(
        @SerializedName("id")
        val id: Int,

        @SerializedName("title")
        val title: String? = null,

        @SerializedName("overview")
        val description: String? = null,

        @SerializedName("poster_path")
        val imagePath: String? = null,

        @SerializedName("release_date")
        val releaseDate: String? = null,
)
