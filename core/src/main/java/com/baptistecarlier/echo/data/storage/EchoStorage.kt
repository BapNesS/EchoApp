package com.baptistecarlier.echo.data.storage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

internal val Context.echoDataStore: DataStore<Preferences> by preferencesDataStore(name = "echo_prefs")

class EchoStorage(youtubeStorage: YoutubeStorage, linkedInStorage: LinkedInStorage) :
    YoutubeStorage by youtubeStorage,
    LinkedInStorage by linkedInStorage
