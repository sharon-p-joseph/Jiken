package com.sharon.jiken.features.main.data.remote.repo

import com.sharon.jiken.features.main.data.remote.api.AnimeApi
import com.sharon.jiken.features.main.domain.models.AnimeModel
import com.sharon.jiken.features.main.domain.repo.AnimeRepo

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import com.sharon.jiken.BuildConfig


class AnimeRepoImpl(
    private val animeAPI: AnimeApi,
    private val dispatcher: CoroutineDispatcher
) : AnimeRepo {
    override suspend fun getAnimeTopList(): AnimeModel =
        withContext(dispatcher) {
            try {
                val response = animeAPI.getAnimeList(BuildConfig.BASE_URL + "top/anime")
                if (response.isSuccessful) {
                    response.body() ?: AnimeModel(result = false, msg = "Empty Body")
                } else {
                    AnimeModel(
                        result = false,
                        msg = response.errorBody()?.string() ?: "Unknown Error"
                    )
                }
            } catch (e: Exception) {
                AnimeModel(result = false, msg = e.localizedMessage ?: "Exception Occurred")
            }
        }


}
