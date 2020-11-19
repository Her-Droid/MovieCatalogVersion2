package id.herdroid.moviecatalog.viewmodel

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class TvShowViewModelTest {
    private lateinit var viewModel: TvShowViewModel

    @Before
    fun setUp(){
        viewModel = TvShowViewModel()
    }

    @Test
    fun getTvShows(){
        val tvShows = viewModel.getTvShows()
        assertNotNull(tvShows)
        assertEquals(10, tvShows.size)
    }
}