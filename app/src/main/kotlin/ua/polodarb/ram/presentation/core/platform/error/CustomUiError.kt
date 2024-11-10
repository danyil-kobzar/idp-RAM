package ua.polodarb.ram.presentation.core.platform.error

import ua.polodarb.idp_ram.R
import ua.polodarb.ram.presentation.core.localization.UiText

sealed class CustomUiError(
    val title: UiText? = null,
    val originalException: Throwable? = null
) : Exception() {

    class BadRequestError(
        originalException: Throwable? = null
    ) : CustomUiError(
        title = UiText.StringResource(R.string.error_invalid_request),
        originalException = originalException
    )

    class UnauthorizedError(
        originalException: Throwable? = null
    ) : CustomUiError(
        title = UiText.StringResource(R.string.error_unauthorized_access),
        originalException = originalException
    )

    class ForbiddenError(
        originalException: Throwable? = null
    ) : CustomUiError(
        title = UiText.StringResource(R.string.error_access_forbidden),
        originalException = originalException
    )

    class NoNetworkError(
        originalException: Throwable? = null
    ) : CustomUiError(
        title = UiText.StringResource(R.string.error_no_network),
        originalException = originalException
    )

    class UnknownError(
        message: String = "An unknown error occurred.",
        originalException: Throwable? = null
    ) : CustomUiError(
        title = UiText.DynamicString(message),
        originalException = originalException
    )
}
