package com.baptistecarlier.echo.data.network

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.logging.*
import io.ktor.http.*

internal fun getHttpClient(baseUrl: String): HttpClient = HttpClient(CIO) {
    defaultRequest {
        url.host = baseUrl
        url.protocol = URLProtocol.HTTPS
    }
    install(Logging) {
        logger = Logger.DEFAULT
        level = LogLevel.HEADERS
    }
    contentNegociators()
    validators()
}

private fun HttpClientConfig<*>.validators() {
    HttpResponseValidator {
        handleResponseExceptionWithRequest { cause, request ->
            if (request.call.response.status.value !in 200..299) {
                throw NetworkException(request.call.response.status.value)
            }
            if (cause !is ClientRequestException) {
                return@handleResponseExceptionWithRequest
            }
            throw NetworkException(cause.response.status.value)
        }
    }
}