package com.sharon.jiken.features.main.domain.models

import com.google.gson.annotations.SerializedName


data class Genres(

    @SerializedName("mal_id") var malId: Int? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("url") var url: String? = null

)