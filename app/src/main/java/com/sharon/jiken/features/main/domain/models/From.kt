package com.sharon.jiken.features.main.domain.models

import com.google.gson.annotations.SerializedName


data class From(

    @SerializedName("day") var day: Int? = null,
    @SerializedName("month") var month: Int? = null,
    @SerializedName("year") var year: Int? = null

)