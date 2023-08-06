package com.bm.newsapps.data.repository

import android.util.Log
import com.bm.newsapps.data.model.ApiResponse
import com.bm.newsapps.data.model.Source
import com.bm.newsapps.data.remote.RemoteDataSource

class NewsRepository(private val remoteDataSource: RemoteDataSource) {
    suspend fun searchNews(query: String, apiKey: String): ApiResponse {
        return remoteDataSource.searchNews(query, apiKey)
    }

    suspend fun getSources(source: String, apiKey: String): ApiResponse{
            return remoteDataSource.getSources(source, apiKey)
    }

    suspend fun getArticleBySource(source: String, apiKey: String): ApiResponse{
        return remoteDataSource.getArticleBySource(source, apiKey)
    }
}
