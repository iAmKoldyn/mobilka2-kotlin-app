package com.example.mobilka2.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.example.mobilka2.R
import com.example.mobilka2.adapter.GifAdapter
import com.example.mobilka2.databinding.FragmentGiphyBinding
import com.example.mobilka2.repository.GiphyRepository
import com.example.mobilka2.viewmodel.GiphyViewModel
import com.example.mobilka2.viewmodel.GiphyViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import com.example.mobilka2.api.ApiServiceProvider


class GiphyFragment : Fragment() {
    private lateinit var viewModel: GiphyViewModel
    private lateinit var gifAdapter: GifAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentGiphyBinding.inflate(inflater, container, false)
        val apiService = ApiServiceProvider.provideApiService()
        val apiKey = getString(R.string.giphy_api_key)
        val repository = GiphyRepository(apiService, apiKey)
        val factory = GiphyViewModelFactory(repository)

        viewModel = ViewModelProvider(this, factory).get(GiphyViewModel::class.java)

        gifAdapter = GifAdapter()
        binding.gifRecyclerView.adapter = gifAdapter

        binding.retryButton.setOnClickListener {
            gifAdapter.retry()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.gifs.collectLatest { pagingData ->
                gifAdapter.submitData(pagingData)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            gifAdapter.loadStateFlow.collectLatest { loadStates ->
                binding.retryButton.visibility = if (loadStates.refresh is LoadState.Error) View.VISIBLE else View.GONE
            }
        }

        return binding.root
    }
}
