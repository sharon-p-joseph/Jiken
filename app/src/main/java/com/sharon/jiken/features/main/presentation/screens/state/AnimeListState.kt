package com.sharon.jiken.features.main.presentation.screens.state

import com.sharon.jiken.features.main.domain.models.Data

data class AnimeListState(
    val status: AnimeListStatus = AnimeListStatus.IDLE,
    val animeList: List<Data> = emptyList(),
    val errorMessage: String = ""
)
enum class AnimeListStatus {
    IDLE,
    LOADING,
    SUCCESS,
    ERROR
}
