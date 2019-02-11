package com.bunk.data.api.response

import com.google.gson.annotations.SerializedName

data class Images(
    val original: Image,
    @SerializedName("downsized_large") val downsizedLarge: Image,
    @SerializedName("fixed_width_small") val fixedWidthSmall: Image,
    val downsized: Image
)