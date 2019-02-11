package com.bunk.domain

import com.bunk.domain.model.Gif
import io.reactivex.Single

interface GiphyDataSource {
    fun getGifs(offset: Int): Single<List<Gif>>
}