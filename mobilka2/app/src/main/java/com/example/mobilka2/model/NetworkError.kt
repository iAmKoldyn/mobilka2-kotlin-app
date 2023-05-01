package com.example.mobilka2.model

sealed class NetworkError : Exception() {
    object NoInternet : NetworkError()
    object ServerError : NetworkError()
}
