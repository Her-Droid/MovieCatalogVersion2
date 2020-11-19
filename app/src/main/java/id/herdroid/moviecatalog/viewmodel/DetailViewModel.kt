package id.herdroid.moviecatalog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.herdroid.moviecatalog.api.source.DataRepository
import id.herdroid.moviecatalog.data.entity.MovieEntity
import id.herdroid.moviecatalog.data.entity.TvShowEntity
import id.herdroid.moviecatalog.data.response.MovieItem
import id.herdroid.moviecatalog.data.response.TvShowItem

class DetailViewModel(private val dataRepository: DataRepository) :ViewModel() {
   private var movId: Int  = 0

    fun setSelectedData(courseId: Int) {
        this.movId = courseId
    }

    fun getMovie(): LiveData<MovieItem> = dataRepository.getMovieById(movId)

    fun getTvShow(): LiveData<TvShowItem> = dataRepository.getTvShowById(movId)

}
