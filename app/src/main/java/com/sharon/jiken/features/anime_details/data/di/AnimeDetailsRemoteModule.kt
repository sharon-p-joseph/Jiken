package com.sharon.jiken.features.anime_details.data.di

import com.sharon.jiken.features.anime_details.data.remote.api.AnimeDetailsApi
import com.sharon.jiken.features.anime_details.data.remote.repo.AnimeDetailsRepoImpl
import com.sharon.jiken.features.anime_details.domain.repo.AnimeDetailsRepo
import com.sharon.jiken.features.anime_details.domain.repo.repo.anime_repo.AnimeDetails
import com.sharon.jiken.features.main.data.remote.api.AnimeApi
import com.sharon.jiken.features.main.data.remote.repo.AnimeRepoImpl
import com.sharon.jiken.features.main.domain.repo.AnimeRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AnimeDetailsRemoteModule {

    @Provides
    @Singleton
    fun provideAnimeDetailsApi(retrofit: Retrofit): AnimeDetailsApi {
        return retrofit.create(AnimeDetailsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAnimeDetailsRepo(
        animeDetailsApi: AnimeDetailsApi,
        dispatcher: CoroutineDispatcher
    ): AnimeDetailsRepo {
        return AnimeDetailsRepoImpl(animeDetailsApi, dispatcher)
    }
}
