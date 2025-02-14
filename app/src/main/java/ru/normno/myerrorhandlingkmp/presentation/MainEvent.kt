package ru.normno.myerrorhandlingkmp.presentation

sealed interface MainEvent {
    data class Error(val error: ru.normno.myerrorhandlingkmp.domain.util.Error?) : MainEvent
    data class Success(val data: String?) : MainEvent
}