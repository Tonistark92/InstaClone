package com.iscoding.myinstegramclone.domain.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState


data class BottomNavigationItem(val icon: ImageVector, val route: Screens)

@Composable
fun BottomNavigationMenu(
    navController: NavController
) {
    var selectedIndex by remember {
        mutableStateOf(0)
    }
    val list = listOf(
        BottomNavigationItem(Icons.Default.Home, Screens.FeedsScreen),
        BottomNavigationItem(Icons.Default.Search, Screens.SearchScreen),
        BottomNavigationItem(Icons.Default.AddCircleOutline, Screens.SearchScreen),
        BottomNavigationItem(Icons.Default.Person, Screens.ProfileScreen)
    )
    val screens = listOf(
        Screens.ProfileScreen.route,
        Screens.FeedsScreen.route,
        Screens.SearchScreen.route,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = screens.any { it == currentDestination?.route }
    if (bottomBarDestination) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(MaterialTheme.colorScheme.background)
        ) {
            list.forEachIndexed { index, item ->
                IconButton(
                    onClick = {
                        selectedIndex = index
                        if (selectedIndex == 0) {
                            navController.navigate(Screens.FeedsScreen.route)
                        } else if (selectedIndex == 1) {
                            navController.navigate(Screens.SearchScreen.route)
                        } else if (selectedIndex == 2) {
                            navController.navigate(Screens.PickImageScreen.route)
                        }
                        else{
                            navController.navigate(Screens.ProfileScreen.route)

                        }
                    }, modifier = Modifier
                        .size(50.dp)
                        .weight(1f)
                        .padding(5.dp)
                ) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = "",
                        tint = if (selectedIndex == index)
                            MaterialTheme.colorScheme.primary
                        else Color.DarkGray,
                        modifier = Modifier.size(30.dp)
                    )

                }
//                Image(
//                    imageVector = item.icon,
//                    contentDescription = "",
//                    modifier = Modifier
//                        .size(40.dp)
//                        .weight(1f)
//                        .padding(5.dp)
//                        .clickable {
//                            selectedIndex = index
//                            if (selectedIndex == 0) {
//                                navController.navigate(Screens.FeedsScreen.route)
//                            } else if (selectedIndex == 1) {
//                                navController.navigate(Screens.SearchScreen.route)
//                            } else if (selectedIndex == 2) {
//                                navController.navigate(Screens.ProfileScreen.route)
//                            }
//                        },
//                    colorFilter =
//                    if (selectedIndex == index)
//                        ColorFilter.tint(MaterialTheme.colorScheme.background)
//                    else ColorFilter.tint(MaterialTheme.colorScheme.background)
//
//                )
            }


        }

    }
}


