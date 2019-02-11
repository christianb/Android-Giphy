package com.bunk.view.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bunk.common.ObserveOnScheduler
import com.bunk.data.api.DEFAULT_LIMIT
import com.bunk.domain.GetGifsUseCase
import com.bunk.domain.model.Gif
import com.bunk.view.R
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy

private val TAG = GiphyListViewModel::class.java.simpleName
const val START_PAGE = 0
const val MAX_PAGE = 20

class GiphyListViewModel(
    private val getGifsUseCase: GetGifsUseCase,
    private val observeOnScheduler: ObserveOnScheduler
) : ViewModel() {

    private val gifLiveData = MutableLiveData<List<Gif>>()
    fun gifLiveData(): LiveData<List<Gif>> = gifLiveData

    private val errorLiveData = MutableLiveData<Int>()
    fun errorLiveData(): LiveData<Int> = errorLiveData

    private var disosable: Disposable? = null

    fun onCreate() {
        loadNext(START_PAGE, MAX_PAGE)
    }

    private fun loadNext(currentPage: Int, maxPage: Int) {
        if (currentPage >= maxPage) {
            return
        }

        val offset = currentPage * DEFAULT_LIMIT
        disosable = getGifsUseCase.getGifs(offset)
            .observeOn(observeOnScheduler.androidMainThreadScheduler)
            .subscribeBy(
                onSuccess = { gifList ->
                    val list = ArrayList<Gif>()

                    gifLiveData.value?.let { oldGifList ->
                        list.addAll(oldGifList)
                    }

                    list.addAll(gifList)

                    gifLiveData.value = list

                    val nextPage: Int = currentPage + 1
                    loadNext(nextPage, maxPage)
                },
                onError = { errorLiveData.value = R.string.gif_load_error }
            )
    }

    override fun onCleared() {
        super.onCleared()

        disosable?.dispose()
    }
}