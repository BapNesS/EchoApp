package com.baptistecarlier.echo.data.storage

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.baptistecarlier.echo.data.storage.LinkedInStorageImpl.PreferencesKeys.linkedInAccessAccessId
import com.baptistecarlier.echo.data.storage.LinkedInStorageImpl.PreferencesKeys.linkedInAccessLinkedInId
import com.baptistecarlier.echo.domain.model.LinkedInAccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface LinkedInStorage {
    suspend fun updateLinkedInAccess(linkedInAccess: LinkedInAccess)
    suspend fun getLinkedInAccess(): Flow<LinkedInAccess>
}

class LinkedInStorageImpl(private val dataStore: DataStore<Preferences>) : LinkedInStorage {

    internal object PreferencesKeys {
        val linkedInAccessAccessId = stringPreferencesKey("linkedInAccessAccessId")
        val linkedInAccessLinkedInId = stringPreferencesKey("linkedInAccessLinkedInId")
    }

    override suspend fun updateLinkedInAccess(linkedInAccess: LinkedInAccess) {
        dataStore.edit {
            it[linkedInAccessAccessId] = linkedInAccess.first
            it[linkedInAccessLinkedInId] = linkedInAccess.second
        }
    }

    override suspend fun getLinkedInAccess() = dataStore.data.map { preferences ->
        val accessId = preferences[linkedInAccessAccessId].orEmpty()
        val linkedInId = preferences[linkedInAccessLinkedInId].orEmpty()
        LinkedInAccess(accessId, linkedInId)
    }
}