package com.bunk.view.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bunk.domain.model.Gif
import com.bunk.util.ImageProvider
import com.bunk.view.R
import kotlinx.android.synthetic.main.gif_viewholder.view.*

class GiphyViewHolder(
    parent: ViewGroup
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.gif_viewholder, parent, false)
) {

    private val imageBuilder = ImageProvider.Builder()

    fun bindData(gif: Gif, gifItemClickListener: GifItemClickListener?) {
        imageBuilder.url(gif.url).into(itemView.gifImageView)
//        imageBuilder.url("http://media2.giphy.com/media/FiGiRei2ICzzG/giphy.gif").into(itemView.gifImageView)

        itemView.setOnClickListener {
            gifItemClickListener?.onItemClick(gif)
        }
    }
}