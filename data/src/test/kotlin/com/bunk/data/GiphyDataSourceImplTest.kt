package com.bunk.data

import com.bunk.common.TestSchedulerProvider
import com.bunk.data.api.GiphyApi
import com.bunk.data.api.response.Image
import com.bunk.data.api.response.Images
import com.bunk.data.api.response.Media
import com.bunk.data.api.response.MediaResponse
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.fail
import org.junit.Test

class GiphyDataSourceImplTest {

    private val giphyApi: GiphyApi = mock()

    private val classToTest: GiphyDataSourceImpl by lazy {
        val testSchedulerProvider = TestSchedulerProvider()

        GiphyDataSourceImpl(
            giphyApi,
            testSchedulerProvider.subscribeOnScheduler
        )
    }

    @Test
    fun `getGifs should return gif list when successful`() {
        val mediaResponse = mock<MediaResponse>()
        val media: Media = mock()
        val mediaList: List<Media> = listOf(media)
        val mediaId = "media_id"
        val url = "url"
        val images = mock<Images>()
        val image = mock<Image>()
        whenever(giphyApi.getGifs()).thenReturn(Single.just(mediaResponse))
        whenever(mediaResponse.data).thenReturn(mediaList)
        whenever(media.id).thenReturn(mediaId)
        whenever(media.images).thenReturn(images)
        whenever(images.downsizedLarge).thenReturn(image)
        whenever(image.url).thenReturn(url)

        classToTest.getGifs().subscribe(
            { gifList ->
                assertThat(gifList.first().id).isEqualTo(mediaId)
                assertThat(gifList.first().url).isEqualTo(url)
                assertThat(gifList).hasSameSizeAs(mediaList)
            },
            { fail("getGifs threw unknown exception") }
        )
    }
}