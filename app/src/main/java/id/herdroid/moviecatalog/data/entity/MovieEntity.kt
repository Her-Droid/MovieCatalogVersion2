package id.herdroid.moviecatalog.data.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieEntity(

    @SerializedName("id")
    val movieId: Int,

    @SerializedName("original_title")
    val title: String,

    @SerializedName("overview")
    val description: String,

    @SerializedName("poster_path")
    val imagePath: String,

    @SerializedName("release_date")
    val releaseDate: String,

) : Parcelable