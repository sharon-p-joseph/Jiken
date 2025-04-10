package com.sharon.jiken.features.anime_details.presentation.intents

sealed class AnimeDetailsIntent {
    data class GetAdminDetails(val adminId: Int) : AnimeDetailsIntent()
}