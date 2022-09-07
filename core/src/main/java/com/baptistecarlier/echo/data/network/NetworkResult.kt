package com.baptistecarlier.echo.data.network

sealed class NetworkResult<T : NetworkResponse> {

    /**
     * An error occurred in the client side
     */
    class ClientError<T : NetworkResponse> :
        NetworkResult<T>()

    /**
     * An error occurred in the backend side
     *
     * @property httpCode HTTP request return code
     * @property message (optional) Additional message
     */
    class ServerError<T : NetworkResponse>(val httpCode: Int, val message: String? = null) :
        NetworkResult<T>()

    /**
     * Result in success
     *
     * @property data Returned data
     */
    class Success<T : NetworkResponse>(val data: T) :
        NetworkResult<T>()
}
