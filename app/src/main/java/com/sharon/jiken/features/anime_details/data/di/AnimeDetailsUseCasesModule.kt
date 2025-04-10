package com.sharon.jiken.features.anime_details.data.di

import com.sharon.jiken.features.anime_details.domain.repo.AnimeDetailsRepo
import com.sharon.jiken.features.anime_details.domain.repo.repo.AnimeDetailsRepoUseCases
import com.sharon.jiken.features.anime_details.domain.repo.repo.anime_repo.AnimeDetails
import com.sharon.jiken.features.main.domain.repo.AnimeRepo
import com.sharon.jiken.features.main.domain.usecase.repo.AnimeRepoUseCases
import com.sharon.jiken.features.main.domain.usecase.repo.anime_repo.AnimeList
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AnimeDetailsRemoteUseCasesModule {

    @Provides
    @Singleton
    fun provideAnimeDetailsRepoUseCases(
        animeDetailsRepo: AnimeDetailsRepo,
    ): AnimeDetailsRepoUseCases {
        return AnimeDetailsRepoUseCases(
            getAnimeDetails = AnimeDetails(animeDetailsRepo)
        )
    }


}