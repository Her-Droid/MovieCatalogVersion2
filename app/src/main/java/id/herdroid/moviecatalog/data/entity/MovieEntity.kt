package id.herdroid.moviecatalog.data.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "Movie")
@Parcelize
data class MovieEntity(

        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "id")
        @SerializedName("id")
        val movieId: Int,

        @ColumnInfo(name = "original_title")
        @SerializedName("original_title")
        val title: String,

        @ColumnInfo(name = "overview")
        @SerializedName("overview")
        val description: String,

        @ColumnInfo(name = "poster_path")
        @SerializedName("poster_path")
        val imagePath: String,

        @ColumnInfo(name = "release_date")
        @SerializedName("release_date")
        val releaseDate: String,

        @ColumnInfo(name = "favorite")
        var favorite: Boolean? = false,

) : Parcelable