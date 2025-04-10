package com.sharon.jiken.features.main.data.remote.api

import com.sharon.jiken.features.main.domain.models.AnimeModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface AnimeApi {
    @GET
    suspend fun getAnimeList(
        @Url url: String,
    ) : Response<AnimeModel>
}