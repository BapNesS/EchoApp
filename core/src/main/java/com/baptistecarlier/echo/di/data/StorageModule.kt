package com.baptistecarlier.echo.di.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.baptistecarlier.echo.data.storage.*
import com.baptistecarlier.echo.data.storage.echoDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object StorageModule {

    @Provides
    fun provideDataStore(context: Context): DataStore<Preferences> = context.echoDataStore

    @Provides
    fun provideYoutubeStorage(dataStore: DataStore<Preferences>): YoutubeStorage =
        YoutubeStorageImpl(dataStore)

    @Provides
    fun provideLinkedInStorage(dataStore: DataStore<Preferences>): LinkedInStorage =
        LinkedInStorageImpl(dataStore)

    @Provides
    fun provideEchoStorage(youtubeStorage: YoutubeStorage, linkedInStorage: LinkedInStorage) =
        EchoStorage(youtubeStorage, linkedInStorage)
}