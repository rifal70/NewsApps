package com.bm.newsapps.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bm.newsapps.BuildConfig
import com.bm.newsapps.data.model.Article
import com.bm.newsapps.data.model.Source
import com.bm.newsapps.data.repository.NewsRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: NewsRepository) : ViewModel() {
    private val _articleList = MutableLiveData<List<Article>>()
    val articleList: LiveData<List<Article>> = _articleList

    private val _sources = MutableLiveData<List<Source>>()
    val sources: LiveData<List<Source>> = _sources

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String> = _toastMessage

    fun searchNews(query: String) {
        viewModelScope.launch {
            try {
                val response = repository.searchNews(query, BuildConfig.NEWS_API_KEY)
                _articleList.value = response.articles
                Log.d("TAG", "searchNews: $response $articleList")
            } catch (e: Exception) {
                _toastMessage.postValue("Error search news: ${e.message}")
                Log.d("TAG", "searchNews: $e")
            }
        }
    }

    fun fetchSources(source: String) {
        viewModelScope.launch {
            try {
                val response = repository.getSources(source, BuildConfig.NEWS_API_KEY)
                _sources.value = response.sources
                Log.d("TAG", "fetchSources: $response $source")
            } catch (e: Exception) {
                _toastMessage.postValue("Error fetching sources: ${e.message}")
                Log.d("TAG", "fetchSources: $e")
            }
        }
    }

    fun getArticleBySource(articleBySource: String) {
        viewModelScope.launch {
            try {
                val response =
                    repository.getArticleBySource(articleBySource, BuildConfig.NEWS_API_KEY)
                _articleList.value = response.articles
                Log.d("TAG", "getArticleBySource: $response $articleBySource")
            } catch (e: Exception) {
                _toastMessage.postValue("Error get article by source: ${e.message}")
                Log.d("TAG", "getArticleBySource: $e")
            }
        }
    }
}
