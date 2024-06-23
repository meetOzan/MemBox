package com.mertozan.membox

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertozan.membox.core.ResponseState
import com.mertozan.membox.domain.usecase.IsUserSigned
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val isUserSignedUseCase: IsUserSigned,
) : ViewModel() {

    private val _splashUiState = MutableStateFlow(SplashUiState.initial())
    val splashUiState = _splashUiState.asStateFlow()

    fun onAction(action: SplashAction) {
        when (action) {
            is SplashAction.NavigateToApp -> isUserSigned()
        }
    }

    private fun isUserSigned() {
        viewModelScope.launch {
            isUserSignedUseCase().collect { responseState ->
                when (responseState) {
                    is ResponseState.Error -> {
                        _splashUiState.value = _splashUiState.value.copy(
                            isError = true,
                            isLoading = false,
                            errorMessage = responseState.message
                        )
                        throw RuntimeException(responseState.message)
                    }

                    ResponseState.Loading -> {
                        _splashUiState.value = _splashUiState.value.copy(
                            isLoading = true
                        )
                    }

                    is ResponseState.Success -> {
                        _splashUiState.value = _splashUiState.value.copy(
                            isLoading = false,
                            isSigned = responseState.data
                        )
                    }
                }
            }
        }
    }
}

data class SplashUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isSigned: Boolean = false,
    val errorMessage: String = ""
) {
    companion object {
        fun initial() = SplashUiState().copy(isLoading = true)
    }
}