package com.bunk.data.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

internal object RetrofitFactory {

    private var retrofit: Retrofit? = null

    fun getRetrofit(): Retrofit {
        val retrofit: Retrofit = retrofit ?: createRetrofit()

        if (this.retrofit == null) {
            this.retrofit = retrofit
        }

        return retrofit
    }

    private fun createRetrofit() = Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(createOkHttp())
        .baseUrl(BASE_URL)
        .build()

    private fun createOkHttp() = OkHttpClient.Builder().build()
}