package com.mertozan.membox.login

sealed class LoginAction {
    data class SignIn(val onNavigate: () -> Unit) :
        LoginAction()

    data class SignUp(val onNavigate: () -> Unit) :
        LoginAction()

    data object IsUserSignedIn : LoginAction()
    data class LoginEmailChanged(val email: String) : LoginAction()
    data class LoginPasswordChanged(val password: String) : LoginAction()
    data class RegisterEmailChanged(val email: String) : LoginAction()
    data class RegisterPasswordChanged(val password: String) : LoginAction()
    data class UsernameChanged(val username: String) : LoginAction()
    data object IsPasswordVisible : LoginAction()
}