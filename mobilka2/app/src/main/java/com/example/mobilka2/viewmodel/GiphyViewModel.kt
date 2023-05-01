package com.example.mobilka2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.mobilka2.api.GiphyPagingSource
import kotlinx.coroutines.flow.Flow
import com.example.mobilka2.model.Gif
import com.example.mobilka2.repository.GiphyRepository

class GiphyViewModel(private val repository: GiphyRepository) : ViewModel() {
    private val apiKey = repository.apiKey

    val gifs: Flow<PagingData<Gif>> = Pager(
        config = PagingConfig(pageSize = 20, enablePlaceholders = false),
        pagingSourceFactory = { GiphyPagingSource(repository.apiService, apiKey) }
    ).flow.cachedIn(viewModelScope)
}
