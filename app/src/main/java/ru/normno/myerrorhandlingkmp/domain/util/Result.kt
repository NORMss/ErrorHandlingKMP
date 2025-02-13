package ru.normno.myerrorhandlingkmp.domain.util

sealed class Result<D, E : Error>(
    val data: D? = null,
    val error: E? = null,
) {
    class Success<D>(
        date: D? = null
    ) : Result<D, ru.normno.myerrorhandlingkmp.domain.util.Error>(
        data = date,
    )

    class Error<D>(
        date: D? = null,
        error: ru.normno.myerrorhandlingkmp.domain.util.Error
    ) : Result<D, ru.normno.myerrorhandlingkmp.domain.util.Error>(
        data = date,
        error = error,
    )
}