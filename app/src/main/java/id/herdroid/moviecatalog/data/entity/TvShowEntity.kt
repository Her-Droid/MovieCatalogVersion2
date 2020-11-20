package id.herdroid.moviecatalog.data.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShowEntity(
    @SerializedName("id")
    val tvShowId: Int,

    @SerializedName("original_name")
    val title: String,

    @SerializedName("overview")
    val description: String,

    @SerializedName("poster_path")
    val imagePath: String,

    @SerializedName("first_air_date")
    val releaseDate: String,
) : Parcelable