package com.baptistecarlier.echo.di

import android.app.Application
import android.content.Context
import com.baptistecarlier.echo.EchoApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): EchoApplication {
        return app as EchoApplication
    }

    @Singleton
    @Provides
    fun provideContext(application: Application): Context = application
}
