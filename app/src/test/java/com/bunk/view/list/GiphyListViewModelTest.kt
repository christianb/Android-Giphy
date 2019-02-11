package com.bunk.view.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.bunk.common.TestSchedulerProvider
import com.bunk.domain.GetGifsUseCase
import com.bunk.domain.model.Gif
import com.bunk.view.R
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt

class GiphyListViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val getGifsUseCase: GetGifsUseCase = mock()
    private val testSchedulerProvider = TestSchedulerProvider()
    private val classToTest: GiphyListViewModel by lazy {
        GiphyListViewModel(
            getGifsUseCase,
            testSchedulerProvider.observeOnScheduler
        )
    }

    @Test
    fun `onCreate should load MAX_PAGE`() {
        val gif: Gif = mock()
        val gifList: List<Gif> = listOf(gif)
        whenever(getGifsUseCase.getGifs(anyInt())).thenReturn(Single.just(gifList))

        classToTest.onCreate()

        verify(getGifsUseCase, times(MAX_PAGE)).getGifs(anyInt())
    }

    @Test
    fun `onCreate should load maxPages`() {
        val gif: Gif = mock()
        val gifList: List<Gif> = listOf(gif)
        whenever(getGifsUseCase.getGifs(anyInt())).thenReturn(Single.just(gifList))

        classToTest.onCreate()

        assertThat(classToTest.gifLiveData().value).hasSize(MAX_PAGE)
    }

    @Test
    fun `onCreate should contain item`() {
        val gif: Gif = mock()
        val gifList: List<Gif> = listOf(gif)
        whenever(getGifsUseCase.getGifs(anyInt())).thenReturn(Single.just(gifList))

        classToTest.onCreate()

        assertThat(classToTest.gifLiveData().value).contains(gif)
    }

    @Test
    fun `onCreate should forward error when failure`() {
        whenever(getGifsUseCase.getGifs(anyInt())).thenReturn(Single.error(Exception()))

        classToTest.onCreate()

        assertThat(classToTest.errorLiveData().value).isEqualTo(R.string.gif_load_error)
    }
}