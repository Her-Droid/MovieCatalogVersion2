package id.herdroid.moviecatalog.data.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieEntity(
    var movieId: String,
    var title: String?,
    var description: String?,
    var releaseDate: String?,
    var imagePath: String
) : Parcelable