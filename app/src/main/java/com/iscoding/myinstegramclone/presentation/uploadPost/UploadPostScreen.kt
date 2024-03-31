package com.iscoding.myinstegramclone.presentation.uploadPost

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.iscoding.myinstegramclone.domain.use_cases.postUseCases.UploadPost
import com.iscoding.myinstegramclone.presentation.profile.UserViewModel

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun UploadPost(navController: NavController) {
    val multiplePermissionsState = rememberMultiplePermissionsState(
        listOf(
            android.Manifest.permission.READ_EXTERNAL_STORAGE
        )
    )
    LaunchedEffect(key1 = true){
        multiplePermissionsState.launchMultiplePermissionRequest()

    }
    if (multiplePermissionsState.allPermissionsGranted) {
        val viewModel: uploadPostViewModel = hiltViewModel()

        LazyVerticalGrid(columns = GridCells.Fixed(3), modifier = Modifier.fillMaxSize()) {
            items(viewModel.pickedPhotos.value.size) {
                Image(
                    painter =rememberImagePainter(viewModel.pickedPhotos.value[it].contentUri) ,
                    contentDescription = "",    
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .border(width = 1.dp, color = Color.White)
                )
            }
        }
    }


}