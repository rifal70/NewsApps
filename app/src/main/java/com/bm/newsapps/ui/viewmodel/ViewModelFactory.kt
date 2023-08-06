package com.bm.newsapps.ui.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bm.newsapps.data.remote.RemoteDataSource
import com.bm.newsapps.data.repository.NewsRepository
import com.bm.newsapps.utils.NetworkUtils

class ViewModelFactory(private val app: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(provideNewsRepository()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }

    private fun provideNewsRepository(): NewsRepository {
        val apiService = NetworkUtils.provideNewsApiService()
        val remoteDataSource = RemoteDataSource(apiService)
        return NewsRepository(remoteDataSource)
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(application: Application): ViewModelFactory {
            return instance ?: synchronized(this) {
                instance ?: ViewModelFactory(application).also { instance = it }
            }
        }
    }
}
