package com.example.mobilka2.model

import com.google.gson.annotations.SerializedName

data class Gif(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("images") val images: GifImages
)

data class GifImages(
    @SerializedName("fixed_width") val fixedWidth: GifImage,
    @SerializedName("fixed_height") val fixedHeight: GifImage
)

data class GifImage(
    @SerializedName("url") val url: String,
    @SerializedName("width") val width: Int,
    @SerializedName("height") val height: Int
)
