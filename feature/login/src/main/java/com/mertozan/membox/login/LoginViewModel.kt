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
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    private val _loginScreenUiState = MutableStateFlow(LoginUiState.initial())
    val loginScreenUiState: StateFlow<LoginUiState> get() = _loginScreenUiState.asStateFlow()

    fun onAction(action: LoginAction) {
        when (action) {
            is LoginAction.SignIn -> signInUser(action.onNavigate)
            is LoginAction.SignUp -> signUpUser(action.onNavigate)
            is LoginAction.IsUserSignedIn -> isUserSigned()
            is LoginAction.LoginEmailChanged -> loginEmailChanged(action.email)
            is LoginAction.LoginPasswordChanged -> loginPasswordChanged(action.password)
            is LoginAction.RegisterEmailChanged -> registerEmailChanged(action.email)
            is LoginAction.RegisterPasswordChanged -> registerPasswordChanged(action.password)
            is LoginAction.UsernameChanged -> usernameChanged(action.username)
            LoginAction.IsPasswordVisible -> onPasswordVisibilityChanged()
        }
    }

    private fun signInUser(onNavigate: () -> Unit) {
        viewModelScope.launch {
            signInUseCase(
                User(
                    email = _loginScreenUiState.value.signInEmail,
                    password = _loginScreenUiState.value.signInPassword
                ),
                onNavigate
            ).collect { responseState ->
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

    private fun signUpUser(onNavigate: () -> Unit) {
        viewModelScope.launch {
            signUpUseCase(
                User(
                    username = _loginScreenUiState.value.username,
                    email = _loginScreenUiState.value.signUpEmail,
                    password = _loginScreenUiState.value.signUpPassword
                ), onNavigate
            ).collect { responseState ->
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

    private fun loginEmailChanged(email: String) {
        _loginScreenUiState.value = _loginScreenUiState.value.copy(
            signInEmail = email
        )
    }

    private fun loginPasswordChanged(password: String) {
        _loginScreenUiState.value = _loginScreenUiState.value.copy(
            signInPassword = password
        )
    }

    private fun registerEmailChanged(email: String) {
        _loginScreenUiState.value = _loginScreenUiState.value.copy(
            signUpEmail = email
        )
    }

    private fun registerPasswordChanged(password: String) {
        _loginScreenUiState.value = _loginScreenUiState.value.copy(
            signUpPassword = password
        )
    }

    private fun usernameChanged(username: String) {
        _loginScreenUiState.value = _loginScreenUiState.value.copy(
            username = username
        )
    }


    private fun onPasswordVisibilityChanged() {
        _loginScreenUiState.value = _loginScreenUiState.value.copy(
            isPasswordVisible = !_loginScreenUiState.value.isPasswordVisible
        )
    }
}

data class LoginUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isSigned: Boolean = false,
    val errorMessage: String = "",
    val memoryList: List<Memory> = listOf(),
    val currentUser: String = "",
    val signInEmail: String = "",
    val signUpEmail: String = "",
    val signInPassword: String = "",
    val signUpPassword: String = "",
    val username: String = "",
    var isPasswordVisible: Boolean = false,
) {
    companion object {
        fun initial() = LoginUiState(isLoading = true)
    }
}