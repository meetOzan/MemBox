package com.mertozan.membox.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mertozan.home.HomeAction
import com.mertozan.home.HomeScreen
import com.mertozan.home.HomeUiState
import com.mertozan.home.HomeViewModel
import com.mertozan.membox.addmemory.AddMemoryScreen
import com.mertozan.membox.addmemory.AddMemoryViewModel
import com.mertozan.membox.login.LoginAction
import com.mertozan.membox.login.LoginScreen
import com.mertozan.membox.login.LoginViewModel
import com.mertozan.membox.profile.ProfileScreen
import com.mertozan.membox.profile.ProfileViewModel

@RequiresApi(Build.VERSION_CODES.O)
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
        homeScreen(
            onAddMemoryNavigate = { navController.navigate(AddMemoryScreen.route) },
            onProfileNavigate = { navController.navigate(ProfileScreen.route) }
        )
        addMemoryScreen(
            onHomeNavigate = { navController.navigate(HomeScreen.route) }
        )
        profileScreen()
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
            onHomeScreenNavigate = onHomeNavigate,
            loginAction = loginViewModel::onAction,
            uiState = loginUiState,
        )
    }
}

fun NavGraphBuilder.homeScreen(
    onAddMemoryNavigate: () -> Unit,
    onProfileNavigate: () -> Unit,
) {
    composable(route = HomeScreen.route/* arguments = HomeScreen.args*/) {

        val homeViewModel = hiltViewModel<HomeViewModel>()
        val homeUiState =
            homeViewModel.homeUiState.collectAsState(initial = HomeUiState.initial()).value

        LaunchedEffect(true) {
            homeViewModel.onAction(HomeAction.GetAllMemories)
        }

        HomeScreen(homeUiState.memoryList, homeUiState, onAddMemoryNavigate, onProfileNavigate)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.addMemoryScreen(
    onHomeNavigate: () -> Unit,
) {
    composable(route = AddMemoryScreen.route) {

        val addMemoryViewModel = hiltViewModel<AddMemoryViewModel>()
        val uiState = addMemoryViewModel.addMemoryState.collectAsState().value

        AddMemoryScreen(onHomeNavigate, addMemoryViewModel::onAction, uiState = uiState)
    }
}

fun NavGraphBuilder.profileScreen() {
    composable(route = ProfileScreen.route) {

        val profileViewModel = hiltViewModel<ProfileViewModel>()
        val profileUiState = profileViewModel.profileUiState.collectAsState().value

        ProfileScreen(profileUiState)
    }
}

