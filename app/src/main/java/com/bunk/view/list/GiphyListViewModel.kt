package com.bunk.view.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bunk.common.ObserveOnScheduler
import com.bunk.domain.GetGifsUseCase
import com.bunk.domain.model.Gif
import com.bunk.view.R
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy

private val TAG = GiphyListViewModel::class.java.simpleName

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
        disosable = getGifsUseCase.getGifs()
            .observeOn(observeOnScheduler.androidMainThreadScheduler)
            .subscribeBy(
                onSuccess = { gifLiveData.value = it },
                onError = { errorLiveData.value = R.string.gif_load_error }
            )
    }

    override fun onCleared() {
        super.onCleared()

        disosable?.dispose()
    }
}