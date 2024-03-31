package com.iscoding.myinstegramclone.presentation.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.iscoding.myinstegramclone.R
import com.iscoding.myinstegramclone.domain.model.User
import com.iscoding.myinstegramclone.presentation.main.components.PostItem
import com.iscoding.myinstegramclone.presentation.main.components.StoryView
import com.iscoding.myinstegramclone.presentation.main.posts.PostViewModel

@Composable
fun FeedsScreen(navController: NavController) {
    val postViewModel: PostViewModel = hiltViewModel()
//    LaunchedEffect(key1 = true) {
//        postViewModel.getAllPosts()
//    }

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    // navigate to camera
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.camera_icon),
                        contentDescription = "",
                        modifier = Modifier.size(25.dp)
                    )

                }
                Icon(
                    painter = painterResource(id = R.drawable.ic_instagram), contentDescription = "",
                    modifier = Modifier.size(60.dp).weight(1f)
                )

                IconButton(onClick = {
                    // navigate to DM messeges

                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.dm_icon),
                        contentDescription = "",
                        modifier = Modifier.size(40.dp)
                    )

                }


            }
        }
        item {
            LazyRow {
                items(10) {
                    val user = User(
                        name = "islam", userName = "Islam Hany", id = "1221sdsdq", "", "",
                        painterResource(id = R.drawable.ic_launcher_background).toString()
                    )
                    StoryView(user)
                }

            }
        }
        items(20) {
            PostItem()
        }


    }


}