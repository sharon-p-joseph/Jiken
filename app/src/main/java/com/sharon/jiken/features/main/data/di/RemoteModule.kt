package com.sharon.jiken.features.main.data.di

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
object RemoteModule {

    @Provides
    @Singleton
    fun provideAnimeApi(retrofit: Retrofit): AnimeApi {
        return retrofit.create(AnimeApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAnimeRepo(
        animeApi: AnimeApi,
        dispatcher: CoroutineDispatcher
    ): AnimeRepo {
        return AnimeRepoImpl(animeApi, dispatcher)
    }
}
