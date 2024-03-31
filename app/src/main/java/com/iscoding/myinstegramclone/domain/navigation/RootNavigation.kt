package com.iscoding.myinstegramclone.domain.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.iscoding.myinstegramclone.presentation.MainPage
import com.iscoding.myinstegramclone.presentation.auth.AuthenticationViewModel

@Composable
fun RootNavigation(
    navController: NavHostController,
    authenticationViewModel: AuthenticationViewModel
) {
    NavHost(
        navController = navController,
        route = Graphs.ROOT,
        startDestination = Graphs.AUTHENTICATION
    ) {

        AuthNavGraph(navController, authenticationViewModel)

        composable(route =  Graphs.PAGES){
            MainPage(navController = navController)
        }


    }
}