package com.sharon.jiken.features.anime_details.domain.repo.repo.anime_repo

import android.util.Log
import com.sharon.jiken.features.anime_details.domain.repo.AnimeDetailsRepo
import com.sharon.jiken.features.main.domain.models.AnimeModel
import com.sharon.jiken.features.main.domain.models.Data
import com.sharon.jiken.features.main.domain.models.DataResponseModel

class AnimeDetails(
    private val animeDetailsRepo: AnimeDetailsRepo
) {
    suspend operator fun invoke(
        animeId: String
    ): DataResponseModel {
        return try {
            animeDetailsRepo.getAnimeDetails(animeId)
        } catch (e: Exception) {
            DataResponseModel(result = false)
        }
    }
}