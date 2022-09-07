package com.baptistecarlier.echo.domain.interactor.linkedin

import com.baptistecarlier.echo.data.storage.EchoStorage
import com.baptistecarlier.echo.domain.model.LinkedInAccess

class StoreLinkedInAccessUc(
    private val echoStorage: EchoStorage
) {
    suspend operator fun invoke(linkedInAccess: LinkedInAccess) {
        echoStorage.updateLinkedInAccess(linkedInAccess)
    }
}
