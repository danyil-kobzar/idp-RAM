package ua.polodarb.ram.data.network.source.base

import android.util.Log
import io.ktor.client.plugins.ResponseException
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.UnknownHostException

interface BaseNetworkSource {

    suspend fun <T> apiCall(call: suspend () -> T): T {
        return withContext(Dispatchers.IO) {
            try {
                call()
            } catch (ex: Exception) {
                Log.e("BaseNetworkSource", ex.stackTraceToString(), ex)
                throw when (ex) {
                    is ResponseException -> handleHttpException(ex.response, ex)
                    is UnknownHostException -> ApiExceptions.NoNetworkException("No internet connection or unknown host", ex)
                    else -> ApiExceptions.UnknownApiException(ex.message, ex)
                }
            }
        }
    }

    private fun handleHttpException(httpResponse: HttpResponse, cause: Throwable): ApiExceptions {
        return when (httpResponse.status.value) {
            400 -> ApiExceptions.BadRequestException("Bad request exception", cause)
            401 -> ApiExceptions.UnauthorizedException("Unauthorized exception", cause)
            403 -> ApiExceptions.ForbiddenException("Forbidden exception", cause)
            404 -> ApiExceptions.NotFoundException("Host not found", cause)
            405 -> ApiExceptions.MethodNotAllowedException("Method not allowed", cause)
            else -> ApiExceptions.UnknownApiException(httpResponse.status.description, cause)
        }
    }
}
