package com.bunk.data

import com.bunk.common.SubscribeOnScheduler
import com.bunk.data.api.GiphyApi
import com.bunk.data.api.response.Media
import com.bunk.domain.GiphyDataSource
import com.bunk.domain.model.Gif
import com.bunk.domain.model.Url
import io.reactivex.Single

class GiphyDataSourceImpl(
    private val giphyApi: GiphyApi,
    private val subscribeOnScheduler: SubscribeOnScheduler
) : GiphyDataSource {
    override fun getGifs(offset: Int): Single<List<Gif>> =
        giphyApi.getGifs(offset)
            .subscribeOn(subscribeOnScheduler.io)
            .map { mediaResponse ->
                mediaResponse.data.map { media -> media.toGif() }
            }

    private fun Media.toGif() = Gif(
        id,
        Url(
            images.downsized.url,
            images.fixedWidthSmall.url
        )
    )
}