package ru.normno.myerrorhandlingkmp.data

import ru.normno.myerrorhandlingkmp.domain.util.Error
import ru.normno.myerrorhandlingkmp.domain.util.RemoteErrorWithCode
import ru.normno.myerrorhandlingkmp.domain.util.Result

class Repository {
    fun getData(number: Int): Result<String, Error> {
        return if (number in 1..10) {
            Result.Success("Yes")
        } else {
            Result.Error(
                date = null,
                error = RemoteErrorWithCode(
                    error = Error.Remote.CLIENT_ERROR,
                    code = 403,
                )
            )
        }
    }
}