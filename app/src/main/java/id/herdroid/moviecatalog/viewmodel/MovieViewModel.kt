package id.herdroid.moviecatalog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.herdroid.moviecatalog.api.source.DataRepository
import id.herdroid.moviecatalog.data.entity.MovieEntity

class MovieViewModel(private val dataRepository: DataRepository) : ViewModel() {


    fun loadMovies(): LiveData<List<MovieEntity>> = dataRepository.getListMovie()
}
