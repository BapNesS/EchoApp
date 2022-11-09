package com.baptistecarlier.echo.data.storage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

private const val DATASTORE_FILENAME = "echo_prefs.preferences_pb"
internal val Context.echoDataStore: DataStore<Preferences> by preferencesDataStore(name = DATASTORE_FILENAME)

class EchoStorage(youtubeStorage: YoutubeStorage, linkedInStorage: LinkedInStorage) :
    YoutubeStorage by youtubeStorage,
    LinkedInStorage by linkedInStorage
