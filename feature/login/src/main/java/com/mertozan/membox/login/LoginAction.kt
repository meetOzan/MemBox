package com.mertozan.membox.login

import com.mertozan.membox.source.dto.User

sealed class LoginAction {
    data class SignIn(val user: User, val onNavigate: () -> Unit) :
        LoginAction()

    data class SignUp(val user: User, val onNavigate: () -> Unit) :
        LoginAction()

    data object IsUserSignedIn : LoginAction()
}