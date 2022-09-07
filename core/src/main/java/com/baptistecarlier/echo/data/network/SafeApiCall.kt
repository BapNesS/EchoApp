package com.baptistecarlier.echo.data.network

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal suspend inline fun <reified T : NetworkResponse> safeApiCall(
    coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO,
    crossinline call: suspend () -> T
): NetworkResult<T> = withContext(coroutineDispatcher) {
    try {
        val success = call.invoke()
        NetworkResult.Success(success)
    } catch (exception: NetworkException) {
        NetworkResult.ServerError(httpCode = exception.httpCode)
    } catch (exception: Exception) {
        NetworkResult.ClientError()
    }
}
