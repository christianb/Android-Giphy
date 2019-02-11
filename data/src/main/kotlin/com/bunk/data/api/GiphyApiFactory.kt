package com.bunk.data.api

object GiphyApiFactory {

    private var giphyApi: GiphyApi? = null

    fun getGiphyApi(): GiphyApi {
        val giphyApi: GiphyApi = giphyApi ?: createGiphyApi()

        if (this.giphyApi == null) {
            this.giphyApi = giphyApi
        }

        return giphyApi
    }

    private fun createGiphyApi() = RetrofitProvider.getRetrofit().create(GiphyApi::class.java)
}