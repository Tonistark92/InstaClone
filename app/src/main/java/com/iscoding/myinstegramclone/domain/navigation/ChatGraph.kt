package com.iscoding.myinstegramclone.domain.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.iscoding.myinstegramclone.domain.model.Message
import com.iscoding.myinstegramclone.presentation.auth.AuthenticationViewModel
import com.iscoding.myinstegramclone.presentation.auth.LogInScreen
import com.iscoding.myinstegramclone.presentation.auth.SignUpScreen
import com.iscoding.myinstegramclone.presentation.splash.SplashScreen

fun NavGraphBuilder.ChatNavGraph(
    navController: NavHostController,
) {

    navigation(
        route = Graphs.CHAT,
        startDestination = Screens.SplashScreen.route,

        ) {
        composable(Screens.ChatDetailsScreen.route) {
//            ChatRoomScreen(
//                listOf(
//                    Message("fjdkdjkj", ""),
//                    Message("fjdkdjkj", ""), Message("fjdkdjkj", "User()"), Message("fjdkdjkj", "")
//                )
//            )
        }
        composable(Screens.ChatListsScreen.route) {
//            ChatListScreen(navController = navController)
        }
        composable(Screens.NotificationsScreen.route) {
//            NotificationsScreen()
        }
        composable(Screens.EditEmailScreen.route) {
//            EditEmailScreen()
        }
        composable(Screens.EditPasswordScreen.route) {
//            EditPasswordScreen()
        }

    }
}