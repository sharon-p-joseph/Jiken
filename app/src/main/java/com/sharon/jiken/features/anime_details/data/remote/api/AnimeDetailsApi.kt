package com.sharon.jiken.features.anime_details.data.remote.api

import com.sharon.jiken.features.main.domain.models.DataResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url


interface AnimeDetailsApi {
    @GET
    suspend fun getAnimeDetails(
        @Url url: String,
    ): Response<DataResponseModel>
}
