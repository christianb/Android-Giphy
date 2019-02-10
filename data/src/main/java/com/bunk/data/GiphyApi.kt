package com.bunk.data

import com.bunk.data.response.MediaResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://api.giphy.com"
private const val API_KEY = "RH6oZROk2Eej9Q8H4CFLyKQ88vJ3qjFT"
private const val DEFAULT_LIMIT = 25

interface GiphyApi {

    @GET("/v1/gifs/trending")
    fun getGifs(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("limit") limit: Int = DEFAULT_LIMIT
    ): Single<MediaResponse>
}