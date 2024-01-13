package com.mertozan.membox.login

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidViewBinding
import com.mertozan.membox.login.databinding.LoginScreenBinding
import com.mertozan.membox.network.dto.User

@Composable
fun LoginScreen(
    onHomeScreenNavigate: () -> Unit,
    loginAction: (LoginAction) -> Unit,
    uiState: LoginUiState,
) {
    if (uiState.isSigned) {
        LaunchedEffect(true) {
            onHomeScreenNavigate()
        }
    } else {
        when {
            uiState.isLoading -> CircularProgressIndicator(color = Color.Red)
            uiState.isError -> {
                throw RuntimeException(uiState.errorMessage)
            }
        }
        AndroidViewBinding(factory = LoginScreenBinding::inflate) {
            with(this) {
                buttonRegister.setOnClickListener {
                    loginAction(
                        LoginAction.SignUp(
                            user = User(
                                name = editTextName.text.toString(),
                                surname = editTextSurname.text.toString(),
                                email = editTextEmail.text.toString(),
                                password = editTextPassword.text.toString()
                            ),
                            onNavigate = onHomeScreenNavigate,
                        )
                    )
                }
                buttonLogin.setOnClickListener {
                    loginAction(
                        LoginAction.SignIn(
                            user = User(
                                email = editTextEmail.text.toString(),
                                password = editTextPassword.text.toString()
                            ),
                            onNavigate = onHomeScreenNavigate,
                        )
                    )
                }
            }
        }
    }
}