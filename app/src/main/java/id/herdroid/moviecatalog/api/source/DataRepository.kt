package id.herdroid.moviecatalog.api.source

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import id.herdroid.moviecatalog.api.NetworkBoundResource
import id.herdroid.moviecatalog.api.remote.RemoteDataRepository
import id.herdroid.moviecatalog.api.remote.RemoteLoadCallback
import id.herdroid.moviecatalog.data.entity.MovieEntity
import id.herdroid.moviecatalog.data.entity.TvShowEntity
import id.herdroid.moviecatalog.data.response.MovieResponse
import id.herdroid.moviecatalog.data.response.TvShowResponse
import id.herdroid.moviecatalog.database.LocalDataSource
import id.herdroid.moviecatalog.utils.AppExecutors
import id.herdroid.moviecatalog.utils.SortUtils
import id.herdroid.moviecatalog.vo.ApiResponse
import id.herdroid.moviecatalog.vo.Resource
import java.util.ArrayList

class DataRepository private constructor(
        private val remoteDataRepository: RemoteDataRepository,
        private val localDataSource: LocalDataSource,
        private val appExecutors: AppExecutors
) : DataSource {
    override fun getAllMovies(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, List<MovieResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(4)
                        .setPageSize(4)
                        .build()
                return LivePagedListBuilder(localDataSource.getMovieDb(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                    data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> =
                    remoteDataRepository.getMovies()

            override fun saveCallResult(data: List<MovieResponse>) {
                val movieList = ArrayList<MovieEntity>()

                for (response in data) {
                    val movie = MovieEntity(
                            movieId  = response.,
                            title = response.title,
                            releaseDate = response.year,
                            description = response.description,
                            imagePath = response.poster,
                    )
                    movieList.add(movie)
                }

                localDataSource.insertMovie(movieList)
            }

        }.asLiveData()
    }

    override fun getAllTvShows(): LiveData<Resource<PagedList<TvShowEntity>>> {
       return object : NetworkBoundResource<PagedList<TvShowEntity>, List<TvShowResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
                val config = PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(4)
                        .setPageSize(4)
                        .build()
                return LivePagedListBuilder(localDataSource.getTvShowDb(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean =
                    data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TvShowResponse>>> =
                    remoteDataRepository.getTvShows()

            override fun saveCallResult(data: List<TvShowResponse>) {
                val tvShowList = ArrayList<TvShowEntity>()

                for (response in data) {
                    val tvShow = TvShowEntity(
                            tvShowId  = response.,
                            title = response.title,
                            releaseDate = response.release,
                            description = response.description,
                            imagePath = response.image,
                    )
                    tvShowList.add(tvShow)
                }

                localDataSource.insertTvShow(tvShowList)
            }

        }.asLiveData()
    }

    override fun getDetailMovie(id: String): LiveData<Resource<MovieEntity>> {
        return object :  NetworkBoundResource<MovieEntity, List<MovieResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<MovieEntity> =
                    localDataSource.getDetailMovie(id)

            override fun shouldFetch(data: MovieEntity?): Boolean =
                    data == null

            override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> =
                    remoteDataRepository.getMovies()

            override fun saveCallResult(data: List<MovieResponse>) {
                val movieList = ArrayList<MovieEntity>()

                for (response in data) {
                    val movie = MovieEntity(
                            movieId  = response.,
                            title = response.title,
                            releaseDate = response.year,
                            description = response.description,
                            imagePath = response.poster,
                    )
                    movieList.add(movie)
                }
                localDataSource.insertMovie(movieList)
            }

        }.asLiveData()
    }

    override fun getDetailTvShow(id: String): LiveData<Resource<TvShowEntity>> {
        return object :  NetworkBoundResource<TvShowEntity, List<TvShowResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<TvShowEntity> =
                    localDataSource.getDetailTvShow(id)

            override fun shouldFetch(data: TvShowEntity?): Boolean =
                    data == null

            override fun createCall(): LiveData<ApiResponse<List<TvShowResponse>>> =
                    remoteDataRepository.getTvShows()

            override fun saveCallResult(data: List<TvShowResponse>) {
                val tvShowList = ArrayList<TvShowEntity>()

                for (response in data) {
                    val tvShow = TvShowEntity(
                            movieId  = response.,
                            title = response.title,
                            releaseDate = response.year,
                            description = response.description,
                            imagePath = response.poster,
                    )
                    tvShowList.add(tvShow)
                }

                localDataSource.insertTvShow(tvShowList)
            }

        }.asLiveData()

    }

    override fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(4)
                .setPageSize(4)
                .build()

        return LivePagedListBuilder(localDataSource.getFavoriteMovie(), config).build()
    }

    override fun getFavoriteTvShows(): LiveData<PagedList<TvShowEntity>> {
        val config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(4)
                .setPageSize(4)
                .build()

        return LivePagedListBuilder(localDataSource.getFavoriteTvShow(), config).build()
    }

    override fun getSortedMovies(sort: String): LiveData<PagedList<MovieEntity>> {
        val query = SortUtils.getSortQueryMovies(sort)
        val config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(4)
                .setPageSize(4)
                .build()

        return LivePagedListBuilder(localDataSource.getSortedMovies(query), config).build()
    }

    override fun getSortedTvShows(sort: String): LiveData<PagedList<TvShowEntity>> {
        val query = SortUtils.getSortQueryTvShows(sort)
        val config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(4)
                .setPageSize(4)
                .build()

        return LivePagedListBuilder(localDataSource.getSortedTvShows(query), config).build()
    }

    override fun setFavoriteMovie(movie: MovieEntity, state: Boolean) {
        appExecutors.diskIO().execute { localDataSource.setMovieFavorite(movie, state) }
    }

    override fun setFavoriteTvShow(tvShow: TvShowEntity, state: Boolean) {
        appExecutors.diskIO().execute { localDataSource.setTvShowFavorite(tvShow, state) }
    }
}
