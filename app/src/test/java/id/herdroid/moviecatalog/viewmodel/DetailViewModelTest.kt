package id.herdroid.moviecatalog.viewmodel

import id.herdroid.moviecatalog.utils.DataDummy
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel
    private val dataMovie = DataDummy.dummyMovies()[0]
    private val movieId = dataMovie.movieId
    private val dataTvShow = DataDummy.dummyTvShows()[0]
    private val tvShowId = dataTvShow.tvShowId

    @Before
    fun setUp() {
        viewModel = DetailViewModel()
        viewModel.setSelectedData(movieId)
        viewModel.setSelectedData(tvShowId)
    }

    @Test
    fun getMovie() {
        viewModel.setSelectedData(dataMovie.movieId)
        val movie = viewModel.getMovie()
        assertNotNull(movie)
        assertEquals(dataMovie.movieId, movie.movieId)
        assertEquals(dataMovie.title, movie.title)
        assertEquals(dataMovie.description, movie.description)
        assertEquals(dataMovie.releaseDate, movie.releaseDate)
        assertEquals(dataMovie.imagePath, movie.imagePath)
    }

    @Test
    fun getTvShow() {
        viewModel.setSelectedData(dataTvShow.tvShowId)
        val tvShow = viewModel.getTvShow()
        assertNotNull(tvShow)
        assertEquals(dataTvShow.tvShowId, tvShow.tvShowId)
        assertEquals(dataTvShow.title, tvShow.title)
        assertEquals(dataTvShow.description, tvShow.description)
        assertEquals(dataTvShow.releaseDate, tvShow.releaseDate)
        assertEquals(dataTvShow.imagePath, tvShow.imagePath)
    }


}