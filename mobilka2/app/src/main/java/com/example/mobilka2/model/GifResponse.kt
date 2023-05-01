package com.example.mobilka2.model

import com.google.gson.annotations.SerializedName

data class GifResponse(
    @SerializedName("data") val data: List<Gif>,
    @SerializedName("pagination") val pagination: Pagination
)

data class Pagination(
    @SerializedName("offset") val offset: Int,
    @SerializedName("count") val count: Int,
    @SerializedName("total_count") val totalCount: Int
)
