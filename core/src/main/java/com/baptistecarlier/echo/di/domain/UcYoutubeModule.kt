package com.baptistecarlier.echo.di.domain

import com.baptistecarlier.echo.data.Cache
import com.baptistecarlier.echo.data.storage.EchoStorage
import com.baptistecarlier.echo.data.youtube.YoutubeRepository
import com.baptistecarlier.echo.domain.interactor.youtube.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(SingletonComponent::class)
object UcYoutubeModule {

    @Provides
    fun provideStoreYoutubeAccessUc(echoStorage: EchoStorage) = StoreYoutubeAccessUc(echoStorage)

    @Provides
    fun provideGetYoutubeAccessUc(echoStorage: EchoStorage) = GetYoutubeAccessUc(echoStorage)

    @Provides
    fun provideGetVideosUc(
        coroutineDispatcher: CoroutineDispatcher,
        youtubeRepository: YoutubeRepository,
        cache: Cache,
        getYoutubeAccessUc: GetYoutubeAccessUc
    ) = GetVideosUc(coroutineDispatcher, youtubeRepository, cache, getYoutubeAccessUc)

    @Provides
    fun provideGetVideoUc(
        coroutineDispatcher: CoroutineDispatcher,
        cache: Cache
    ) = GetVideoUc(coroutineDispatcher, cache)

    @Provides
    fun provideGetChannelInfoUc(
        coroutineDispatcher: CoroutineDispatcher,
        youtubeRepository: YoutubeRepository,
        getYoutubeAccessUc: GetYoutubeAccessUc
    ) = GetChannelInfoUc(coroutineDispatcher, youtubeRepository, getYoutubeAccessUc)
}
