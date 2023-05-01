package com.example.mobilka2.api

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.mobilka2.model.NetworkError
import retrofit2.HttpException
import java.io.IOException
import com.example.mobilka2.model.Gif

class GiphyPagingSource(
    private val apiService: ApiService,
    private val apiKey: String
) : PagingSource<Int, Gif>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Gif> {
        val position = params.key ?: 0
        return try {
            val response = apiService.getTrendingGifs(apiKey, position, params.loadSize)
            val gifs = response.data
            LoadResult.Page(
                data = gifs,
                prevKey = if (position == 0) null else position - params.loadSize,
                nextKey = if (gifs.isEmpty()) null else position + params.loadSize
            )
        } catch (exception: IOException) {
            LoadResult.Error(NetworkError.NoInternet)
        } catch (exception: HttpException) {
            LoadResult.Error(NetworkError.ServerError)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Gif>): Int? {
        return state.anchorPosition
    }
}
