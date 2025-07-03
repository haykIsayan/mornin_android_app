package com.example.project_mornin.data.newsapi

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NewsApiService {

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String? = null,
        @Query("category") category: String? = null,
        @Query("q") query: String? = null,
        @Query("pageSize") pageSize: Int? = null,
        @Query("page") page: Int? = null,
        @Query("apiKey") apiKey: String = "b824bfb98f294e4b943c170840f3e423"
    ): NewsResponse

    @GET("everything")
    suspend fun getEverything(
        @Query("q") query: String? = null,
        @Query("from") from: String? = null,
        @Query("to") to: String? = null,
        @Query("sortBy") sortBy: String? = null,
        @Query("pageSize") pageSize: Int? = null,
        @Query("page") page: Int? = null,
        @Query("apiKey") apiKey: String = "b824bfb98f294e4b943c170840f3e423"
    ): NewsResponse
//
//    @GET("sources")
//    suspend fun getSources(
//        @Query("category") category: String? = null,
//        @Query("language") language: String? = null,
//        @Query("country") country: String? = null,
//        @Query("apiKey") apiKey: String = "b824bfb98f294e4b943c170840f3e423"
//    ): SourcesResponse
}