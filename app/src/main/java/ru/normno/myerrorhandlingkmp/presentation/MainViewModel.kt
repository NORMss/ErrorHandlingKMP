package ru.normno.myerrorhandlingkmp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ru.normno.myerrorhandlingkmp.data.Repository
import ru.normno.myerrorhandlingkmp.domain.util.Result

class MainViewModel(
    private val repository: Repository,
) : ViewModel() {
    private val eventChanel = Channel<MainEvent>()
    val event = eventChanel.receiveAsFlow()

    init {
        viewModelScope.launch {
            val result = repository.getData(11)
            when (result) {
                is Result.Error -> {
                    eventChanel.send(MainEvent.Error(result.error))
                }

                is Result.Success -> {
                    eventChanel.send(MainEvent.Success(result.data))
                }
            }

        }
    }
}