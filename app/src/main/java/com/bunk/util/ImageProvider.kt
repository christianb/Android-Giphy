package com.bunk.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl

class ImageProvider {
    class Builder {
        private lateinit var url: String

        fun url(url: String): Builder {
            this.url = url
            return this
        }

        fun into(imageView: ImageView) {
            Glide.with(imageView).asGif().load(GlideUrl(url)).into(imageView)
        }
    }
}