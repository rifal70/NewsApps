package com.bm.newsapps.data.remote

import com.bm.newsapps.data.model.ApiResponse
import com.bm.newsapps.utils.NewsApiService

class RemoteDataSource(private val newsApiService: NewsApiService) {
    suspend fun searchNews(query: String, apiKey: String): ApiResponse {
        return newsApiService.searchNews(query, apiKey)
    }

    suspend fun getSources(sources: String, apiKey: String): ApiResponse {
        return newsApiService.getSources(sources, apiKey)
    }

    suspend fun getArticleBySource(sources: String, apiKey: String): ApiResponse {
        return newsApiService.getArticleBySource(sources, apiKey)
    }
}
