package id.herdroid.moviecatalog.di

import android.content.Context
import id.herdroid.moviecatalog.api.ApiClient
import id.herdroid.moviecatalog.api.ApiService
import id.herdroid.moviecatalog.api.remote.RemoteDataRepository
import id.herdroid.moviecatalog.api.source.DataRepository

object Injection {
    fun provideRepository(context: Context): DataRepository {
        val remoteDataRepository = RemoteDataRepository.getInstance(
            ApiClient().getApi().create(ApiService::class.java)
        )
        return DataRepository.getInstance(remoteDataRepository)
    }
}