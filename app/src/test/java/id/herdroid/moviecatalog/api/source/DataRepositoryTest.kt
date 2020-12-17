package id.herdroid.moviecatalog.api.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import org.mockito.Mockito.mock
import id.herdroid.moviecatalog.api.remote.RemoteDataRepository
import id.herdroid.moviecatalog.utils.DataDummy
import id.herdroid.moviecatalog.utils.LiveDataTestUtil
import org.junit.Rule
import org.junit.Test

class DataRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataRepository::class.java)
    private val dataRepository = FakeDataRepository(remote)

    private val movieResponse =  DataDummy.dummyMovies()
    private val movieId  = movieResponse[0].movieId

    private val tvShowResponse =  DataDummy.dummyTvShows()
    private val tvShowId = tvShowResponse[0].tvShowId

    private val dummyMovies = DataDummy.dummyMovies()[0]
    private val dummyTvShow = DataDummy.dummyTvShows()[0]

    @Test
    fun getListMovie() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteLoadCallback.LoadMoviesCallback)
                .onAllMoviesReceived(movieResponse)
            null
        }.`when`(remote).getMovies(any())
        val movieEntities = LiveDataTestUtil.getValue(dataRepository.getListMovie())
        verify(remote).getMovies(any())
        assertNotNull(movieEntities)
        assertEquals(movieResponse.size.toLong(), movieEntities.size.toLong())
    }

    @Test
    fun getMovieById(){
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteLoadCallback.LoadDetailMovieCallback)
                .onDetailMovieReceived(dummyMovies)
            null
        }.`when`(remote).getDetailMovie(eq(movieId), any())

        val movieEntities = LiveDataTestUtil.getValue(dataRepository.getMovieById(movieId))
        verify(remote).getDetailMovie(eq(movieId), any())
        assertNotNull(movieEntities.title)
        assertEquals(dummyMovies.title, movieEntities.title)
        assertNotNull(movieEntities.description)
        assertEquals(dummyMovies.description, movieEntities.description)
        assertNotNull(movieEntities.releaseDate)
        assertEquals(dummyMovies.releaseDate, movieEntities.releaseDate)
        assertNotNull(movieEntities.imagePath)
        assertEquals(dummyMovies.imagePath, movieEntities.imagePath)
    }

    @Test
    fun getListTvShow(){
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteLoadCallback.LoadTvShowsCallback)
                .onAllTvShowsReceived(tvShowResponse)
            null
        }.`when`(remote).getTvShows(any())
        val tvShowEntities = LiveDataTestUtil.getValue(dataRepository.getListTvShow())
        verify(remote).getTvShows(any())
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponse.size.toLong(), tvShowEntities.size.toLong())
    }

    @Test
    fun getTvShowById(){
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteLoadCallback.LoadDetailTvShowCallback)
                .onDetailTvShowsReceived(dummyTvShow)
            null
        }.`when`(remote).getDetailTvShow(eq(tvShowId), any())

        val tvShowEntities = LiveDataTestUtil.getValue(dataRepository.getTvShowById(tvShowId))
        verify(remote).getDetailTvShow(eq(tvShowId), any())
        assertNotNull(tvShowEntities.title)
        assertEquals(dummyTvShow.title, tvShowEntities.title)
        assertNotNull(tvShowEntities.description)
        assertEquals(dummyTvShow.description, tvShowEntities.description)
        assertNotNull(tvShowEntities.releaseDate)
        assertEquals(dummyTvShow.releaseDate, tvShowEntities.releaseDate)
        assertNotNull(tvShowEntities.imagePath)
        assertEquals(dummyTvShow.imagePath, tvShowEntities.imagePath)
    }




}