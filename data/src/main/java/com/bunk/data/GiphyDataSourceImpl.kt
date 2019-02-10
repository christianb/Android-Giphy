package com.bunk.data

import com.bunk.domain.GiphyDataSource
import com.bunk.domain.model.Gif
import com.bunk.domain.util.SubscribeOnScheduler
import io.reactivex.Single

class GiphyDataSourceImpl(
    private val giphyApi: GiphyApi,
    private val subscribeOnScheduler: SubscribeOnScheduler
) : GiphyDataSource {
    override fun getGifs(): Single<List<Gif>> =
        giphyApi.getGifs()
            .subscribeOn(subscribeOnScheduler.io)
            .map { mediaResponse ->
                mediaResponse.data.map { media ->
                    Gif(media.id, media.images.downsizedLarge.url)
                }
            }
}