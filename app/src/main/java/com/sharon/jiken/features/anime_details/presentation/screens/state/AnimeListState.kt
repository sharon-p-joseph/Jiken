package com.sharon.jiken.features.anime_details.presentation.screens.state

import com.sharon.jiken.features.main.domain.models.Data


data class AnimeListDetailsState(
    val status: AnimeDetailsStatus = AnimeDetailsStatus.IDLE,
    val animeList: Data? = null,
)

enum class AnimeDetailsStatus {
    IDLE,
    LOADING,
    SUCCESS,
    ERROR
}
