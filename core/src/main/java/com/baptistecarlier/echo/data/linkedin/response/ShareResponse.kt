package com.baptistecarlier.echo.data.linkedin.response

import com.baptistecarlier.echo.data.network.NetworkResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ShareResponse(
    @SerialName("activity")
    val activity: String,
) : NetworkResponse
