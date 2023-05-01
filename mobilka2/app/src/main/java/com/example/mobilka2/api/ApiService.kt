package com.example.mobilka2.api

import retrofit2.http.GET
import retrofit2.http.Query
import com.example.mobilka2.model.GifResponse

interface ApiService {
    @GET("v1/gifs/trending")
    suspend fun getTrendingGifs(
        @Query("api_key") apiKey: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): GifResponse
}
