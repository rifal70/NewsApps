package com.bm.newsapps.utils

import com.bm.newsapps.data.model.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("v2/everything")
    suspend fun searchNews(
        @Query("q") query: String,
        @Query("apiKey") apiKey: String
    ): ApiResponse

    @GET("v2/sources")
    suspend fun getSources(
        @Query("sources") category: String,
        @Query("apiKey") apiKey: String
    ): ApiResponse


    @GET("v2/top-headlines")
    suspend fun getArticleBySource(
        @Query("sources") category: String,
        @Query("apiKey") apiKey: String
    ): ApiResponse
}

