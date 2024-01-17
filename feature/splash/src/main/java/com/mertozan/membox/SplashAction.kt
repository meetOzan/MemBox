package com.mertozan.membox

sealed class SplashAction {
    data object NavigateToApp : SplashAction()
}