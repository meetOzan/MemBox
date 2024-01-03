package com.mertozan.membox.navigation

interface Destination {
    val route: String
}

object LoginScreen : Destination {
    override val route = "login_screen"
}

object HomeScreen : Destination {
    override val route = "home_screen"
}

const val ARGS_NAME = "name"