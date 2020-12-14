package id.herdroid.moviecatalog.database.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import id.herdroid.moviecatalog.data.entity.TvShowEntity

@Dao
interface TvShowDao {

    @Query("SELECT * FROM TvShow")
    fun getTvShowDb(): LiveData<List<TvShowEntity>>

    @Query("SELECT * FROM TvShow WHERE id = :tvShowId")
    fun getTvShowDbById(tvShowId: Int?): LiveData<TvShowEntity>

    @Query("SELECT * FROM TvShow WHERE favorite = 1")
    fun getFavoriteTvShow(): DataSource.Factory<Int, TvShowEntity>

    @RawQuery(observedEntities = [TvShowEntity::class])
    fun getSortedTvShows(query: SupportSQLiteQuery): DataSource.Factory<Int, TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShow(tvShow: List<TvShowEntity>)

    @Update
    fun updateTvShow(tvShow: TvShowEntity)

    @Delete
    fun deleteTvShow(tvShow: TvShowEntity)

}