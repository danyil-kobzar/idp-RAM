package ua.polodarb.ram.data.network.source.base

import android.util.Log
import io.ktor.client.plugins.ResponseException
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ua.polodarb.ram.presentation.core.platform.error.ApiExceptions
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
                    is UnknownHostException -> ApiExceptions.NoNetworkException(cause = ex)
                    else -> ApiExceptions.UnknownApiException(cause = ex)
                }
            }
        }
    }

    private fun handleHttpException(httpResponse: HttpResponse, cause: Throwable): ApiExceptions {
        return when (httpResponse.status.value) {
            400 -> ApiExceptions.BadRequestException(cause = cause)
            401 -> ApiExceptions.UnauthorizedException(cause = cause)
            403 -> ApiExceptions.ForbiddenException(cause = cause)
            404 -> ApiExceptions.NotFoundException(cause = cause)
            405 -> ApiExceptions.MethodNotAllowedException(cause = cause)
            else -> ApiExceptions.UnknownApiException(cause = cause)
        }
    }
}
