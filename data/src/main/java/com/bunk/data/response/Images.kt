package com.bunk.data.response

import com.google.gson.annotations.SerializedName

data class Images(
    val original: Image,
    @SerializedName("downsized_large") val downsizedLarge: Image,
    val downsized: Image
)