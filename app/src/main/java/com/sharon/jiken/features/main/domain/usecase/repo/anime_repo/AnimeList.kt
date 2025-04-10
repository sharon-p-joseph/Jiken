package com.sharon.jiken.features.main.domain.usecase.repo.anime_repo

import com.sharon.jiken.features.main.domain.models.AnimeModel
import com.sharon.jiken.features.main.domain.repo.AnimeRepo

class AnimeList(
    private val animeRepo: AnimeRepo
) {
    suspend operator fun invoke(
    ): AnimeModel? {
        return try {
            animeRepo.getAnimeTopList()
        } catch (e: Exception) {
            null
        }
    }
}