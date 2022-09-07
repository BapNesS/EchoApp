package com.baptistecarlier.echo.domain.interactor.linkedin

import com.baptistecarlier.echo.data.storage.EchoStorage
import kotlinx.coroutines.flow.first

class GetLinkedInAccessUc(private val echoStorage: EchoStorage) {
    suspend operator fun invoke() = asFlow().first()
    suspend fun asFlow() = echoStorage.getLinkedInAccess()
}
