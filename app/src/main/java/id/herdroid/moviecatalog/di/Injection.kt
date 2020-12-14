package id.herdroid.moviecatalog.di

import id.herdroid.moviecatalog.api.network.ApiClient
import id.herdroid.moviecatalog.api.network.ApiService
import id.herdroid.moviecatalog.api.remote.RemoteDataRepository
import id.herdroid.moviecatalog.api.source.DataRepository

object Injection {
    fun provideRepository(): DataRepository {
        val remoteDataRepository = RemoteDataRepository.getInstance(
            ApiClient().getApi().create(ApiService::class.java)
        )
        return DataRepository.getInstance(remoteDataRepository)
    }
}