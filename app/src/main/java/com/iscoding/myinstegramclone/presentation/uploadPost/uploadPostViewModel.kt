package com.iscoding.myinstegramclone.presentation.uploadPost

import android.content.ContentUris
import android.content.Context
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.iscoding.myinstegramclone.domain.model.PickedImage
import com.iscoding.myinstegramclone.domain.use_cases.postUseCases.PostsUseCase
import com.iscoding.myinstegramclone.domain.use_cases.userusercases.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class uploadPostViewModel @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore,
    private val postsUseCase: PostsUseCase,
    @ApplicationContext private val context: Context
) : ViewModel() {
    val pickedPhotos = mutableStateOf<List<PickedImage>>(emptyList())

    init {
        viewModelScope.launch {
            pickedPhotos.value = loadLatestImagesFromExternalStorage(context)
            Log.d("TAG", pickedPhotos.value.toString() )
        }
    }


    suspend fun loadLatestImagesFromExternalStorage(context: Context): List<PickedImage> {
        return withContext(Dispatchers.Default) {
            val collection = sdk29AndUp {
                MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)
            } ?: MediaStore.Images.Media.EXTERNAL_CONTENT_URI

            val projection = arrayOf(
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.WIDTH,
                MediaStore.Images.Media.HEIGHT
            )

            val photos = mutableListOf<PickedImage>()

            context.contentResolver.query(
                collection,
                projection,
                null,
                null,
                "${MediaStore.Images.Media.DATE_ADDED} DESC" // Sort by date added in descending order
            )?.use { cursor ->
                val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
                val displayNameColumn =
                    cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)
                val widthColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.WIDTH)
                val heightColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.HEIGHT)

                while (cursor.moveToNext()) {
                    val id = cursor.getLong(idColumn)
                    val displayName = cursor.getString(displayNameColumn)
                    val width = cursor.getInt(widthColumn)
                    val height = cursor.getInt(heightColumn)
                    val contentUri = ContentUris.withAppendedId(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        id
                    )
                    photos.add(PickedImage(id, displayName, width, height, contentUri))
                }
                photos.toList()
            } ?: listOf()
        }
    }
}

inline fun <T> sdk29AndUp(onSdk29: () -> T): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        onSdk29()
    } else null
}