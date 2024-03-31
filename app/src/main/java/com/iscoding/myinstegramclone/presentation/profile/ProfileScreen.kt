package com.iscoding.myinstegramclone.presentation.profile

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalProvider
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.google.android.gms.common.api.Response
import com.iscoding.myinstegramclone.R
import com.iscoding.myinstegramclone.domain.model.TabModel
import com.iscoding.myinstegramclone.domain.navigation.BottomNavigationItem
import com.iscoding.myinstegramclone.presentation.profile.components.ActionButton
import com.iscoding.myinstegramclone.presentation.profile.components.MyProfile
import com.iscoding.myinstegramclone.presentation.profile.components.PostsSection
import com.iscoding.myinstegramclone.presentation.profile.components.ProfileStates
import com.iscoding.myinstegramclone.presentation.profile.components.RoundedImage
import com.iscoding.myinstegramclone.presentation.profile.components.TabView
import com.iscoding.myinstegramclone.presentation.search.SearchScreen
import com.iscoding.myinstegramclone.util.MyResponse

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {
    val viewModel: UserViewModel = hiltViewModel()

    when (val response = viewModel.getUserData.value) {
        is MyResponse.Loading -> {
            CircularProgressIndicator()
        }


        is MyResponse.Success -> {
            Log.d("Tag", response.data.toString() + " PROFILE")
            if (response.data != null) {
                val selectedTapIndex = remember {
                    mutableIntStateOf(0)
                }
                val obj = response.data
                    Column(modifier = Modifier.fillMaxSize()) {
                        TopAppBar(
                            title = {
                                Text(
                                    text = "obj.name",
                                    fontWeight = FontWeight.Bold,
                                    fontStyle = FontStyle.Italic,
                                    fontSize = 25.sp
                                )
                            },
                            actions = {
                                Icon(
                                    imageVector = Icons.Default.AddBox,
                                    contentDescription = "",
                                    modifier = Modifier.size(30.dp)
                                )
                                Spacer(modifier = Modifier.size(10.dp))
                                Icon(
                                    imageVector = Icons.Default.Settings,
                                    contentDescription = "",
                                    modifier = Modifier.size(30.dp)
                                )

                            },
                            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
//                                containerColor = Color.White,

                                )
                        )
                        Column(modifier = Modifier
                            .fillMaxWidth()
                            )
                        {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        start = 10.dp,
                                        end = 20.dp,
                                        top = 10.dp,
                                        bottom = 10.dp
                                    ),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RoundedImage(
                                    image = painterResource(id = R.drawable.mine),
                                    modifier = Modifier
                                        .size(100.dp)
                                        .weight(3.5f)
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceAround,
                                ) {
                                    ProfileStates(
                                        numberText = "133",
                                        text = "Posts",
                                        navController = navController
                                    )
                                    ProfileStates(
                                        numberText = "13300",
                                        text = "Follower",
                                        navController = navController
                                    )
                                    ProfileStates(
                                        numberText = "following",
                                        text = "Following",
                                        navController = navController
                                    )

                                }

                            }
                        }
                        //my profile
                        MyProfile(
                            displayName = "obj.name",
                            bio = "obj.bio",
                            url = "obj.url"
                        )
                        Spacer(modifier = Modifier.height(10.dp))


                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.padding(horizontal = 20.dp)
                        )
                        {
                            ActionButton(
                                text = "Edit Profile",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(35.dp)
                            )

                        }
                        Spacer(modifier = Modifier.height(15.dp))

                        TabView(
                            tabModels = listOf(
                                TabModel(
                                    image = painterResource(id = R.drawable.grid),
                                    text = "Posts"
                                ),
                                TabModel(
                                    image = painterResource(id = R.drawable.video),
                                    text = "videos"
                                ),
                                TabModel(
                                    image = painterResource(id = R.drawable.tv),
                                    text = "TV"
                                )
                            ),
                        ) {
                            selectedTapIndex.value = it
                        }
                        when (selectedTapIndex.value) {
                            0 -> {
                                PostsSection(
                                    posts = listOf(
                                        painterResource(id = R.drawable.mine),
                                        painterResource(id = R.drawable.mine),
                                        painterResource(id = R.drawable.mine),
                                        painterResource(id = R.drawable.mine),
                                        painterResource(id = R.drawable.mine),
                                        painterResource(id = R.drawable.mine),
                                        painterResource(id = R.drawable.mine),
                                        painterResource(id = R.drawable.mine),
                                        painterResource(id = R.drawable.mine),
                                        painterResource(id = R.drawable.mine),
                                        painterResource(id = R.drawable.mine),
                                        painterResource(id = R.drawable.mine),
                                        painterResource(id = R.drawable.mine),
                                        painterResource(id = R.drawable.mine),
                                        painterResource(id = R.drawable.mine),
                                        painterResource(id = R.drawable.mine),
                                        painterResource(id = R.drawable.mine),
                                        painterResource(id = R.drawable.mine),
                                        painterResource(id = R.drawable.mine),
                                    ),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(300.dp)
                                        .padding(5.dp)
                                )
                            }

                            1 -> {}
                            2 -> {}
                        }
                    }
                }


        }

        is MyResponse.Error -> {
            Toast.makeText(LocalContext.current, response.message, Toast.LENGTH_LONG).show()
        }
    }

}
@Preview(showBackground = true)
@Composable
fun ShowPreview() {
//ProfileScreen(rememberNavController())
}
