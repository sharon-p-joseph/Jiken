package com.sharon.jiken.features.main.domain.models

import com.google.gson.annotations.SerializedName


data class Prop(

    @SerializedName("from") var from: From? = From(),
    @SerializedName("to") var to: To? = To()

)