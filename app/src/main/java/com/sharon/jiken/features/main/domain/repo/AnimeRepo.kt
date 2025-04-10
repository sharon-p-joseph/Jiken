package com.sharon.jiken.features.main.domain.repo

import com.sharon.jiken.features.main.domain.models.AnimeModel

interface AnimeRepo {
    /**function to call the anime list from the api*/
    suspend fun getAnimeTopList(): AnimeModel
}