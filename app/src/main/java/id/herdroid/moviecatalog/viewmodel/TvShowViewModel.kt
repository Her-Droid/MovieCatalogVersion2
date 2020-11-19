package id.herdroid.moviecatalog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.herdroid.moviecatalog.api.source.DataRepository
import id.herdroid.moviecatalog.data.entity.TvShowEntity
import id.herdroid.moviecatalog.data.response.TvShowItem

class TvShowViewModel(private val dataRepository: DataRepository) : ViewModel() {
    var page = 1
    fun getTvShow(): LiveData<List<TvShowItem>> = dataRepository.getListTvShow(page)
}
