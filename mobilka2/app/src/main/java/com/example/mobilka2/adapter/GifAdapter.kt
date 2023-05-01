package com.example.mobilka2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mobilka2.databinding.GifItemLayoutBinding
import com.example.mobilka2.model.Gif
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class GifAdapter : PagingDataAdapter<Gif, GifAdapter.GifViewHolder>(GIF_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifViewHolder {
        return GifViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: GifViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class GifViewHolder(private val binding: GifItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(gif: Gif) {
            val requestOptions = RequestOptions().transform(CenterCrop(), RoundedCorners(8))

            Glide.with(itemView)
                .asGif()
                .apply(requestOptions)
                .load(gif.images.fixedWidth.url)
                .into(binding.gifImageView)
        }

        companion object {
            fun create(parent: ViewGroup): GifViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = GifItemLayoutBinding.inflate(inflater, parent, false)
                return GifViewHolder(binding)
            }
        }
    }

    companion object {
        private val GIF_COMPARATOR = object : DiffUtil.ItemCallback<Gif>() {
            override fun areItemsTheSame(oldItem: Gif, newItem: Gif): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Gif, newItem: Gif): Boolean {
                return oldItem == newItem
            }
        }
    }
}