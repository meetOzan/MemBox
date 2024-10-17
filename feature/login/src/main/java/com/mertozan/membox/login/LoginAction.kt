package com.mertozan.membox.login

sealed class LoginAction {
    data class SignIn(val onNavigate: () -> Unit) :
        LoginAction()

    data class SignUp(val onNavigate: () -> Unit) :
        LoginAction()

    data object IsUserSignedIn : LoginAction()
    data class EmailChanged(val email: String) : LoginAction()
    data class PasswordChanged(val password: String) : LoginAction()
    data class UsernameChanged(val username: String) : LoginAction()
    data object IsPasswordVisible : LoginAction()
}