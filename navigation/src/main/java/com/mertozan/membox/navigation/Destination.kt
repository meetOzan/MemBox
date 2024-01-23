package com.mertozan.membox.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.mertozan.membox.core.consts.Constants.ARGS_NAME

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
    fun navigateWithArgs(
        name: String,
    ): String = "$route/$name"

    val routeWithArgs = "$route/{$ARGS_NAME}"
    val args = listOf(
        navArgument(ARGS_NAME) { type = NavType.StringType },
    )
}

object ProfileScreen : Destination {
    override val route = "profile_screen"
}