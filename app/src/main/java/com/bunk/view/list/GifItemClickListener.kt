package com.bunk.view.list

import com.bunk.domain.model.Gif

interface GifItemClickListener {
    fun onItemClick(gif: Gif)
}