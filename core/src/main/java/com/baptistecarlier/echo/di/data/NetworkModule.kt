package com.baptistecarlier.echo.di.data

import com.baptistecarlier.echo.data.linkedin.LinkedInRepository
import com.baptistecarlier.echo.data.network.getHttpClient
import com.baptistecarlier.echo.data.youtube.YoutubeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named

const val YOUTUBE_CLIENT = "YOUTUBE_CLIENT"
const val LINKEDIN_CLIENT = "LINKEDIN_CLIENT"

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideNetworkCoroutineDispatcher() = Dispatchers.IO

    // *********** Youtube ***********

    @Provides
    fun provideYoutubeRepository(
        coroutineDispatcher: CoroutineDispatcher,
        @Named(YOUTUBE_CLIENT) client: HttpClient
    ) = YoutubeRepository(coroutineDispatcher, client)

    @Provides
    @Named(YOUTUBE_CLIENT)
    fun provideHttpClientYoutube(): HttpClient = getHttpClient("www.googleapis.com")

    // *********** LinkedIn ***********

    @Provides
    fun provideLinkedInRepository(
        coroutineDispatcher: CoroutineDispatcher,
        @Named(LINKEDIN_CLIENT) client: HttpClient
    ) = LinkedInRepository(coroutineDispatcher, client)

    @Provides
    @Named(LINKEDIN_CLIENT)
    fun provideHttpClientLinkedIn(): HttpClient = getHttpClient("api.linkedin.com")
}
