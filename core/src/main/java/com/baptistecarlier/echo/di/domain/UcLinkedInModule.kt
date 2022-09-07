package com.baptistecarlier.echo.di.domain

import com.baptistecarlier.echo.data.linkedin.LinkedInRepository
import com.baptistecarlier.echo.data.storage.EchoStorage
import com.baptistecarlier.echo.domain.interactor.linkedin.GetLinkedInAccessUc
import com.baptistecarlier.echo.domain.interactor.linkedin.PostOnLinkedInUc
import com.baptistecarlier.echo.domain.interactor.linkedin.StoreLinkedInAccessUc
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(SingletonComponent::class)
object UcLinkedInModule {

    @Provides
    fun provideStoreLinkedInAccessUc(echoStorage: EchoStorage) = StoreLinkedInAccessUc(echoStorage)

    @Provides
    fun provideGetLinkedInAccessUc(echoStorage: EchoStorage) = GetLinkedInAccessUc(echoStorage)

    @Provides
    fun providePostOnLinkedInUc(
        coroutineDispatcher: CoroutineDispatcher,
        linkedInRepository: LinkedInRepository,
        getLinkedInAccessUc: GetLinkedInAccessUc
    ) = PostOnLinkedInUc(coroutineDispatcher, linkedInRepository, getLinkedInAccessUc)
}
