package com.bm.newsapps.data.model
import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("sources")
    val sources: List<Source>,
    @SerializedName("articles")
    val articles: List<Article>
)

data class Source(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)

data class Article(
    @SerializedName("author")
    val author: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("urlToImage")
    val urlToImage: String?,
    @SerializedName("publishedAt")
    val publishedAt: String?
)
