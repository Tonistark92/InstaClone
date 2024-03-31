package com.iscoding.myinstegramclone.domain.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.iscoding.myinstegramclone.presentation.auth.AuthenticationViewModel
import com.iscoding.myinstegramclone.presentation.auth.LogInScreen
import com.iscoding.myinstegramclone.presentation.auth.SignUpScreen
import com.iscoding.myinstegramclone.presentation.main.FeedsScreen
import com.iscoding.myinstegramclone.presentation.profile.ProfileScreen
import com.iscoding.myinstegramclone.presentation.search.SearchScreen
import com.iscoding.myinstegramclone.presentation.splash.SplashScreen


fun NavGraphBuilder.AuthNavGraph(
    navController: NavHostController,
    authenticationViewModel: AuthenticationViewModel
) {

    navigation(
        route = Graphs.AUTHENTICATION,
        startDestination = Screens.SplashScreen.route,

        ) {
        composable(Screens.SplashScreen.route) {
            SplashScreen(navController = navController, authViewModle = authenticationViewModel)
        }
        composable(Screens.LogInScreen.route) {
            LogInScreen(navController = navController, authenticationViewModel)
        }
        composable(Screens.SignUpScreen.route) {
            SignUpScreen(navController = navController, authenticationViewModel)
        }
    }
}