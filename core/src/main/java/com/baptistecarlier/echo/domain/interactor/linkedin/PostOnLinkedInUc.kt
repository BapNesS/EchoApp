package com.baptistecarlier.echo.domain.interactor.linkedin

import com.baptistecarlier.echo.data.linkedin.LinkedInRepository
import com.baptistecarlier.echo.data.network.NetworkResult
import com.baptistecarlier.echo.domain.model.YoutubeVideo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class PostOnLinkedInUc(
    private val coroutineDispatcher: CoroutineDispatcher,
    private val linkedInRepository: LinkedInRepository,
    private val getLinkedInAccessUc: GetLinkedInAccessUc,
) {

    suspend operator fun invoke(linkedInPostContent: String, youtubeVideo: YoutubeVideo): Boolean = withContext(coroutineDispatcher) {
        val linkedInAccess = getLinkedInAccessUc()
        val response = linkedInRepository.post(
            linkedInAccess,
            linkedInPostContent,
            youtubeVideo
        )
        return@withContext response is NetworkResult.Success
    }
}
