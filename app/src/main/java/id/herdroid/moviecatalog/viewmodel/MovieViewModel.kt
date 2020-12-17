package id.herdroid.moviecatalog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import id.herdroid.moviecatalog.api.source.DataRepository
import id.herdroid.moviecatalog.data.entity.MovieEntity
import id.herdroid.moviecatalog.vo.Resource

class MovieViewModel(private val dataRepository: DataRepository) : ViewModel() {

    fun loadMovies(): LiveData<Resource<PagedList<MovieEntity>>> = dataRepository.getAllMovies()

    fun getMovieFavorite(): LiveData<PagedList<MovieEntity>> = dataRepository.getFavoriteMovies()
}
