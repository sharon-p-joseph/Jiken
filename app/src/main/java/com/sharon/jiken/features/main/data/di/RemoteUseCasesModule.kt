package com.sharon.jiken.features.main.data.di

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
object RemoteUseCasesModule {

    @Provides
    @Singleton
    fun provideAnimeRepoUseCases(
        animeRepo: AnimeRepo,
    ): AnimeRepoUseCases {
        return AnimeRepoUseCases(
        getAnimeList = AnimeList(animeRepo)
        )
    }


}