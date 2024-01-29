package com.mertozan.membox.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.mertozan.membox.core.ResponseState
import com.mertozan.membox.domain.usecase.IsUserSigned
import com.mertozan.membox.domain.usecase.SignInUseCase
import com.mertozan.membox.domain.usecase.SignUpUseCase
import com.mertozan.membox.model.Memory
import com.mertozan.membox.model.User
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
    private val firebaseAuth: FirebaseAuth,
) : ViewModel() {

    private val _loginScreenUiState = MutableStateFlow(LoginUiState.initial())
    val loginScreenUiState: StateFlow<LoginUiState> get() = _loginScreenUiState.asStateFlow()

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
                        _loginScreenUiState.value = _loginScreenUiState.value.copy(
                            isError = true,
                            isLoading = false,
                            errorMessage = responseState.message
                        )
                        throw RuntimeException(responseState.message)
                    }

                    ResponseState.Loading -> {
                        _loginScreenUiState.value = _loginScreenUiState.value.copy(
                            isLoading = true,
                        )
                    }

                    is ResponseState.Success -> {
                        _loginScreenUiState.value = _loginScreenUiState.value.copy(
                            isLoading = false
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
                        _loginScreenUiState.value = _loginScreenUiState.value.copy(
                            isError = true,
                            isLoading = false,
                            errorMessage = responseState.message
                        )
                        throw RuntimeException(responseState.message)
                    }

                    ResponseState.Loading -> {
                        _loginScreenUiState.value = _loginScreenUiState.value.copy(
                            isLoading = true
                        )
                    }

                    is ResponseState.Success -> {
                        _loginScreenUiState.value = _loginScreenUiState.value.copy(
                            isLoading = false,
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
                        _loginScreenUiState.value = _loginScreenUiState.value.copy(
                            isError = true,
                            isLoading = false,
                            errorMessage = responseState.message
                        )
                        throw RuntimeException(responseState.message)
                    }

                    ResponseState.Loading -> {
                        _loginScreenUiState.value = _loginScreenUiState.value.copy(
                            isLoading = true
                        )
                    }

                    is ResponseState.Success -> {
                        _loginScreenUiState.value = _loginScreenUiState.value.copy(
                            isLoading = false,
                            isSigned = responseState.data,
                            currentUser = firebaseAuth.currentUser.toString()
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
    val errorMessage: String = "",
    val memoryList: List<Memory> = listOf(),
    val currentUser: String = "",
) {
    companion object {
        fun initial() = LoginUiState(isLoading = true)
    }
}