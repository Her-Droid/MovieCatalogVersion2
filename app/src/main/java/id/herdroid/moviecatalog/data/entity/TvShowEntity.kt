package id.herdroid.moviecatalog.data.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShowEntity(
    val tvShowId: String,
    var title: String,
    var description: String,
    var releaseDate: String,
    var imagePath: String
) : Parcelable