package com.bunk.domain

class GetGifsUseCase(
    private val giphyDataSource: GiphyDataSource
) {
    fun getGifs() = giphyDataSource.getGifs()
}