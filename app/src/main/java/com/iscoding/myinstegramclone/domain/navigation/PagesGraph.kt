package com.iscoding.myinstegramclone.domain.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.iscoding.myinstegramclone.presentation.main.FeedsScreen
import com.iscoding.myinstegramclone.presentation.profile.ProfileScreen
import com.iscoding.myinstegramclone.presentation.search.SearchScreen
import com.iscoding.myinstegramclone.presentation.uploadPost.UploadPost

@Composable
fun PagesNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graphs.PAGES,
        startDestination = Screens.FeedsScreen.route
    ) {
        composable(
            route = Screens.FeedsScreen.route,
        ) {
            FeedsScreen(navController = navController)

        }
        composable(
            route = Screens.SearchScreen.route,
        )
        {
            SearchScreen()
        }
        composable(
            route = Screens.PickImageScreen.route,
        )
        {
            UploadPost(navController = navController)
        }

        composable(
            route = Screens.ProfileScreen.route,
        )
        {
            ProfileScreen(navController = navController)

        }
        composable(route = Graphs.CHAT) {
            ChatNavGraph(navController = navController)
        }


    }
}