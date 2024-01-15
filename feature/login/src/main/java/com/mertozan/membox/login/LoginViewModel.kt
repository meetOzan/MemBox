package com.mertozan.membox.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertozan.membox.core.ResponseState
import com.mertozan.membox.domain.IsUserSigned
import com.mertozan.membox.domain.SignInUseCase
import com.mertozan.membox.domain.SignUpUseCase
import com.mertozan.membox.source.network.dto.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val signUpUseCase: SignUpUseCase,
    private val isUserSignedUseCase: IsUserSigned,
) : ViewModel() {

    private val _characterScreenUiState = MutableStateFlow(LoginUiState.initial())
    val characterScreenUiState: StateFlow<LoginUiState> get() = _characterScreenUiState.asStateFlow()

    fun onAction(action: LoginAction) {
        when (action) {
            is LoginAction.SignIn -> signInUser(action.user, action.onNavigate)
            is LoginAction.SignUp -> signUpUser(action.user, action.onNavigate)
            is LoginAction.IsUserSignedIn -> isUserSigned()
        }
    }

    private fun signInUser(user: User, onNavigate: () -> Unit) {
        viewModelScope.launch {
            signInUseCase(user, onNavigate).collect { responseState ->
                when (responseState) {
                    is ResponseState.Error -> {
                        _characterScreenUiState.value = _characterScreenUiState.value.copy(
                            isError = true,
                            isLoading = false,
                            errorMessage = responseState.message
                        )
                        throw RuntimeException(responseState.message)
                    }

                    ResponseState.Loading -> {
                        _characterScreenUiState.value = _characterScreenUiState.value.copy(
                            isLoading = true
                        )
                    }

                    is ResponseState.Success -> {
                        _characterScreenUiState.value = _characterScreenUiState.value.copy(
                            isLoading = false,
                            isScreenEnable = true
                        )
                    }
                }
            }
        }
    }

    private fun signUpUser(user: User, onNavigate: () -> Unit) {
        viewModelScope.launch {
            signUpUseCase(user, onNavigate).collect { responseState ->
                when (responseState) {
                    is ResponseState.Error -> {
                        _characterScreenUiState.value = _characterScreenUiState.value.copy(
                            isError = true,
                            isLoading = false,
                            errorMessage = responseState.message
                        )
                        throw RuntimeException(responseState.message)
                    }

                    ResponseState.Loading -> {
                        _characterScreenUiState.value = _characterScreenUiState.value.copy(
                            isLoading = true
                        )
                    }

                    is ResponseState.Success -> {
                        _characterScreenUiState.value = _characterScreenUiState.value.copy(
                            isLoading = false,
                            isScreenEnable = true
                        )
                    }
                }
            }
        }
    }

    private fun isUserSigned() {
        viewModelScope.launch {
            isUserSignedUseCase().collect { responseState ->
                when (responseState) {
                    is ResponseState.Error -> {
                        _characterScreenUiState.value = _characterScreenUiState.value.copy(
                            isError = true,
                            isLoading = false,
                            errorMessage = responseState.message
                        )
                        throw RuntimeException(responseState.message)
                    }

                    ResponseState.Loading -> {
                        _characterScreenUiState.value = _characterScreenUiState.value.copy(
                            isLoading = true
                        )
                    }

                    is ResponseState.Success -> {
                        _characterScreenUiState.value = _characterScreenUiState.value.copy(
                            isLoading = false,
                            isSigned = responseState.data
                        )
                    }
                }
            }
        }
    }
}

data class LoginUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isSigned: Boolean = false,
    val isScreenEnable: Boolean = false,
    val errorMessage: String = "",
) {
    companion object {
        fun initial() = LoginUiState(isLoading = true)
    }
}