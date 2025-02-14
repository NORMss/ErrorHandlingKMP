package ru.normno.myerrorhandlingkmp.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.normno.myerrorhandlingkmp.data.Repository
import ru.normno.myerrorhandlingkmp.domain.util.RemoteErrorWithCode
import ru.normno.myerrorhandlingkmp.presentation.ui.theme.MyErrorHandlingKMPTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyErrorHandlingKMPTheme {
                val viewModel: MainViewModel by viewModels(
                    factoryProducer = {
                        object : ViewModelProvider.Factory {
                            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                                return MainViewModel(
                                    repository = Repository()
                                ) as T
                            }
                        }
                    }
                )
                LaunchedEffect(true) {
                    viewModel.event.collect {
                        when (it) {
                            is MainEvent.Error -> {
                                if (it.error is RemoteErrorWithCode && it.error.code in (400..499)) {
                                    Toast.makeText(
                                        this@MainActivity,
                                        it.error.code.toString(),
                                        Toast.LENGTH_SHORT,
                                    ).show()
                                }
                            }

                            is MainEvent.Success -> {
                                Toast.makeText(
                                    this@MainActivity,
                                    it.data.toString(),
                                    Toast.LENGTH_SHORT,
                                ).show()
                            }
                        }
                    }
                }
            }
        }
    }
}