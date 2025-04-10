package com.sharon.jiken.features.main.domain.models

import com.google.gson.annotations.SerializedName


data class AnimeModel(

    var result: Boolean = true,
    var msg: String? = null,
    @SerializedName("pagination") var pagination: Pagination? = Pagination(),
    @SerializedName("data") var data: ArrayList<Data> = arrayListOf()

)