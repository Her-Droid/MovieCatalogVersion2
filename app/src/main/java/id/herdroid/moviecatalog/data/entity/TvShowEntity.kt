package id.herdroid.moviecatalog.data.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "TvShow")
@Parcelize
data class TvShowEntity(

        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "id")
        @SerializedName("id")
        val tvShowId: Int,

        @ColumnInfo(name = "original_name")
        @SerializedName("original_name")
        val title: String,

        @ColumnInfo(name = "overview")
        @SerializedName("overview")
        val description: String,

        @ColumnInfo(name = "poster_path")
        @SerializedName("poster_path")
        val imagePath: String,

        @ColumnInfo(name = "first_air_date")
        @SerializedName("first_air_date")
        val releaseDate: String,

        @ColumnInfo(name = "favorite")
        var favorite: Boolean? = false

) : Parcelable