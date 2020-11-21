package id.herdroid.moviecatalog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.herdroid.moviecatalog.api.source.DataRepository
import id.herdroid.moviecatalog.data.entity.MovieEntity
import id.herdroid.moviecatalog.data.entity.TvShowEntity

class DetailViewModel(private val dataRepository: DataRepository) :ViewModel() {
    private var movId : Int = 0

    fun setSelectedData(courseId: Int) {
        this.movId = courseId
    }

    fun getMovie(): LiveData<MovieEntity> = dataRepository.getMovieById(movId)

    fun getTvShow(): LiveData<TvShowEntity> = dataRepository.getTvShowById(movId)

}
