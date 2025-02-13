package ru.normno.myerrorhandlingkmp.domain.util

sealed interface Error {
    enum class Remote : Error {
        REQUEST_TIMEOUT_ERROR,
        TO_MANY_REQUESTS_ERROR,
        CLIENT_ERROR,
        REDIRECTION_ERROR,
        SERVER_ERROR,
        NO_INTERNET_ERROR,
        SERIALIZATION_ERROR,
        UNKNOWN,
    }

    enum class LocalError : Error {
        DISK_FULL,
        UNKNOWN,
    }
}