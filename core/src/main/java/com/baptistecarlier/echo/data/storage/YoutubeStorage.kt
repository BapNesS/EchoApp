package com.baptistecarlier.echo.data.storage

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.baptistecarlier.echo.data.storage.YoutubeStorageImpl.PreferencesKeys.youtubeAccessApiKey
import com.baptistecarlier.echo.data.storage.YoutubeStorageImpl.PreferencesKeys.youtubeAccessChannelId
import com.baptistecarlier.echo.domain.model.YoutubeAccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface YoutubeStorage {
    suspend fun updateYoutubeAccess(youtubeAccess: YoutubeAccess)
    suspend fun getYoutubeAccess(): Flow<YoutubeAccess>
}

class YoutubeStorageImpl(private val dataStore: DataStore<Preferences>) : YoutubeStorage {

    internal object PreferencesKeys {
        val youtubeAccessApiKey = stringPreferencesKey("youtubeAccessApiKey")
        val youtubeAccessChannelId = stringPreferencesKey("youtubeAccessChannelId")
    }

    override suspend fun updateYoutubeAccess(youtubeAccess: YoutubeAccess) {
        dataStore.edit {
            it[youtubeAccessApiKey] = youtubeAccess.first
            it[youtubeAccessChannelId] = youtubeAccess.second
        }
    }

    override suspend fun getYoutubeAccess() = dataStore.data.map { preferences ->
        val apiKey = preferences[youtubeAccessApiKey].orEmpty()
        val channelId = preferences[youtubeAccessChannelId].orEmpty()
        YoutubeAccess(apiKey, channelId)
    }
}