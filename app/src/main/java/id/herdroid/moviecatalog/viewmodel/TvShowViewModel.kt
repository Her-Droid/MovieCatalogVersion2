package id.herdroid.moviecatalog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.herdroid.moviecatalog.api.source.DataRepository
import id.herdroid.moviecatalog.data.entity.MovieEntity
import id.herdroid.moviecatalog.data.entity.TvShowEntity

class TvShowViewModel(private var dataRepository: DataRepository) : ViewModel() {

    fun loadTvShow(): LiveData<List<TvShowEntity>> = dataRepository.getListTvShow()
}
