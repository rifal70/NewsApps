package com.bm.newsapps.utils

import com.bm.newsapps.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkUtils {
    private fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                    .header("X-Api-Key", BuildConfig.NEWS_API_KEY)
                val request = requestBuilder.build()
                chain.proceed(request)
            }
            .build()
    }

    private fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://newsapi.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(createOkHttpClient())
            .build()
    }

    fun provideNewsApiService(): NewsApiService {
        return provideRetrofit().create(NewsApiService::class.java)
    }
}

