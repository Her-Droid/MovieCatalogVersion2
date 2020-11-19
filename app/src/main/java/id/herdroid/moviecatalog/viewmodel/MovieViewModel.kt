package id.herdroid.moviecatalog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.herdroid.moviecatalog.api.source.DataRepository
import id.herdroid.moviecatalog.data.response.MovieItem

class MovieViewModel(private val dataRepository: DataRepository) : ViewModel() {
    var page = 1
    fun getMovies(): LiveData<List<MovieItem>> = dataRepository.getListMovie(page)
}
