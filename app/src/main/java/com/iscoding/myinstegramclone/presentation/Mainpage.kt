package com.iscoding.myinstegramclone.presentation

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.iscoding.myinstegramclone.domain.navigation.BottomNavigationMenu
import com.iscoding.myinstegramclone.domain.navigation.PagesNavGraph

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainPage(
    navController: NavHostController
) {
    var navHome: NavHostController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigationMenu(
                navController = navHome
            )
        }
    ) { padding ->
        PagesNavGraph(navHome)

    }


}