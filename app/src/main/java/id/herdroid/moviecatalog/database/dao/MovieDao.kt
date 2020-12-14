package id.herdroid.moviecatalog.database.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import id.herdroid.moviecatalog.data.entity.MovieEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM Movie")
    fun getMovieDb(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM Movie WHERE id = :movieId")
    fun getMovieDbById(movieId: Int?): LiveData<MovieEntity>

    @Query("SELECT * FROM Movie WHERE favorite = 1")
    fun getFavoriteMovie(): DataSource.Factory<Int, MovieEntity>

    @RawQuery(observedEntities = [MovieEntity::class])
    fun getSortedMovies(query: SupportSQLiteQuery): DataSource.Factory<Int, MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: List<MovieEntity>)

    @Update
    fun updateMovie(movie: MovieEntity)

    @Delete
    fun deleteMovie(movie: MovieEntity)

}