package com.bunk.domain

import com.bunk.domain.model.Gif
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class GetGifsUseCaseTest {

    private val giphyDataSource: GiphyDataSource = mock()

    private val classToTest: GetGifsUseCase by lazy {
        GetGifsUseCase(giphyDataSource)
    }

    @Test
    fun `getGifs should return list`() {
        val gif: Gif = mock()
        val gifList: List<Gif> = listOf(gif)
        whenever(giphyDataSource.getGifs()).thenReturn(Single.just(gifList))

        var resultList: List<Gif>? = null
        classToTest.getGifs()
            .subscribe(
                { resultList = it },
                { /* no implementation */ }
            )

        assertThat(resultList).containsExactly(gif)
    }
}