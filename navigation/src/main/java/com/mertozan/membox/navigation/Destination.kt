package com.mertozan.membox.navigation

interface Destination {
    val route: String
}

object SplashScreen : Destination {
    override val route = "splash_screen"
}

object LoginScreen : Destination {
    override val route = "login_screen"
}

object HomeScreen : Destination {
    override val route = "home_screen"
}

object AddMemoryScreen : Destination {
    override val route = "add_memory_screen"
}

object MemoryDetailScreen : Destination {
    override val route = "memory_detail_screen"
}

object ProfileScreen : Destination {
    override val route = "profile_screen"
}

const val ARGS_NAME = "name"