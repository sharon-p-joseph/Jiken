package com.sharon.jiken.features.main.domain.models

import com.google.gson.annotations.SerializedName


data class Trailer(

    @SerializedName("youtube_id") var youtubeId: String? = null,
    @SerializedName("url") var url: String? = null,
    @SerializedName("embed_url") var embedUrl: String? = null,
    @SerializedName("images") var images: Images? = Images()

)