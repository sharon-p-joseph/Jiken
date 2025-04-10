package com.sharon.jiken.features.anime_details.domain.repo
import com.sharon.jiken.features.main.domain.models.DataResponseModel


interface AnimeDetailsRepo {
    suspend fun getAnimeDetails(animeId: String): DataResponseModel
}