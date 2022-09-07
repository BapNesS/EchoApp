package com.baptistecarlier.echo.di.domain

import com.baptistecarlier.echo.domain.interactor.content.FormatContentUc
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UcContentModule {
    @Provides
    fun provideFormatContentUc() = FormatContentUc()
}