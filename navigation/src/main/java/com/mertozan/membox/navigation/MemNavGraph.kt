package com.mertozan.membox.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mertozan.home.HomeScreen
import com.mertozan.membox.login.LoginAction
import com.mertozan.membox.login.LoginScreen
import com.mertozan.membox.login.LoginViewModel

@Composable
fun MemNavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController, startDestination = LoginScreen.route
    ) {
        loginScreen(
            onHomeNavigate = { navController.navigate(HomeScreen.route) }
        )
        homeScreen()
    }
}

fun NavGraphBuilder.loginScreen(
    onHomeNavigate: () -> Unit,
) {
    composable(route = LoginScreen.route) {

        val loginViewModel = hiltViewModel<LoginViewModel>()
        val loginUiState = loginViewModel.characterScreenUiState.collectAsState().value

        LaunchedEffect(key1 = true) {
            loginViewModel.onAction(LoginAction.IsUserSignedIn)
        }

        LoginScreen(
            onHomeNavigate,
            loginViewModel::onAction,
            uiState = loginUiState
        )
    }
}

fun NavGraphBuilder.homeScreen() {
    composable(route = HomeScreen.route/* arguments = HomeScreen.args*/) {
        HomeScreen()
    }
}

