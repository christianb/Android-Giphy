package com.bunk.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl

class ImageProvider {
    private var url: String? = null

    fun url(url: String): ImageProvider {
        this.url = url
        return this
    }

    fun into(imageView: ImageView) {
        Glide.with(imageView.context)
            .asGif()
            .load(GlideUrl(url))
            .into(imageView)
    }
}