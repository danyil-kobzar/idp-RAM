package ua.polodarb.ram.common.core.result

sealed class ResultOf<T>(
    val data: T? = null,
    val error: Throwable? = null
) {
    class Loading<T>() : ResultOf<T>()
    class Success<T>(data: T) : ResultOf<T>(data = data)
    class Error<T>(error: Throwable) : ResultOf<T>(error = error)
}