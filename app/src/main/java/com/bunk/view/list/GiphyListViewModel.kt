package com.bunk.view.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bunk.domain.GetGifsUseCase
import com.bunk.domain.model.Gif
import com.bunk.domain.util.ObserveOnScheduler
import io.reactivex.rxkotlin.subscribeBy

private val TAG = GiphyListViewModel::class.java.simpleName

class GiphyListViewModel(
    private val getGifsUseCase: GetGifsUseCase,
    private val observeOnScheduler: ObserveOnScheduler
) : ViewModel() {

    private val gifLiveData = MutableLiveData<List<Gif>>()
    fun gifLiveData(): LiveData<List<Gif>> = gifLiveData

    fun onCreate() {
        getGifsUseCase.getGifs()
            .observeOn(observeOnScheduler.androidMainThreadScheduler)
            .subscribeBy(
                onSuccess = {
                    gifLiveData.value = it
                },

                onError = {
                    Log.d(TAG, "")
                }
            )
    }
}