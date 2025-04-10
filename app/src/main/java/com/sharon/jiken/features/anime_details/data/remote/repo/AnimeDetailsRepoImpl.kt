package com.sharon.jiken.features.anime_details.data.remote.repo

import android.util.Log
import com.sharon.jiken.BuildConfig
import com.sharon.jiken.features.anime_details.data.remote.api.AnimeDetailsApi
import com.sharon.jiken.features.anime_details.domain.repo.AnimeDetailsRepo
import com.sharon.jiken.features.main.domain.models.Data
import com.sharon.jiken.features.main.domain.models.DataResponseModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext


class AnimeDetailsRepoImpl(
    private val animeDetails: AnimeDetailsApi, private val dispatcher: CoroutineDispatcher
) : AnimeDetailsRepo {
    override suspend fun getAnimeDetails(animeId: String): DataResponseModel =
        withContext(dispatcher) {
            try {
                val response =
                    animeDetails.getAnimeDetails(BuildConfig.BASE_URL + "anime" + "/${animeId}")
                if (response.isSuccessful) {
                    val responseBody = response.body() as DataResponseModel
                    responseBody.copy(result = true)
                } else {
                    DataResponseModel(result = false)
                }

            } catch (e: Exception) {
                DataResponseModel(result = false)
            }
        }

}
