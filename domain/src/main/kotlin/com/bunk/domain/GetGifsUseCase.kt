package com.bunk.domain

class GetGifsUseCase(
    private val giphyDataSource: GiphyDataSource
) {
    fun getGifs(offset: Int) = giphyDataSource.getGifs(offset)
}