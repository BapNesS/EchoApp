package com.baptistecarlier.echo.domain.interactor.youtube

import com.baptistecarlier.echo.data.storage.EchoStorage
import com.baptistecarlier.echo.domain.model.YoutubeAccess

class StoreYoutubeAccessUc(
    private val echoStorage: EchoStorage
) {
    suspend operator fun invoke(youtubeAccess: YoutubeAccess) {
        echoStorage.updateYoutubeAccess(youtubeAccess)
    }
}