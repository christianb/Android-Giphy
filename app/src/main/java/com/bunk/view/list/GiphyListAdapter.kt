package com.bunk.view.list

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bunk.domain.model.Gif

class GiphyListAdapter : ListAdapter<Gif, GiphyViewHolder>(DIFF_UTIL_ITEM_CALLBACK) {

    var gifItemClickListener: GifItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = GiphyViewHolder(parent)

    override fun onBindViewHolder(holder: GiphyViewHolder, position: Int) {
        holder.bindData(getItem(position), gifItemClickListener)
    }

    companion object {
        private val DIFF_UTIL_ITEM_CALLBACK =
            object : DiffUtil.ItemCallback<Gif>() {
                override fun areItemsTheSame(oldItem: Gif, newItem: Gif) =
                    oldItem.id == newItem.id

                override fun areContentsTheSame(oldItem: Gif, newItem: Gif) =
                    oldItem == newItem
            }
    }
}