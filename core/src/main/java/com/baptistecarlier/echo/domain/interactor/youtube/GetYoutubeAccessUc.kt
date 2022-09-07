package com.baptistecarlier.echo.domain.interactor.youtube

import com.baptistecarlier.echo.data.storage.EchoStorage
import kotlinx.coroutines.flow.first

class GetYoutubeAccessUc(private val echoStorage: EchoStorage) {
    suspend operator fun invoke() = asFlow().first()
    suspend fun asFlow() = echoStorage.getYoutubeAccess()
}