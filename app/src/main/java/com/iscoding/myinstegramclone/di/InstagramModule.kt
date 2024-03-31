package com.iscoding.myinstegramclone.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.iscoding.myinstegramclone.data.AuthenticationRepositoryImp
import com.iscoding.myinstegramclone.data.UserRepositoryImp
import com.iscoding.myinstegramclone.data.postRepositoryImp
import com.iscoding.myinstegramclone.domain.repository.AuthenticationRepository
import com.iscoding.myinstegramclone.domain.repository.UserRepository
import com.iscoding.myinstegramclone.domain.repository.postRepository
import com.iscoding.myinstegramclone.domain.use_cases.authusercases.AuthenticationUseCases
import com.iscoding.myinstegramclone.domain.use_cases.authusercases.FirebaseAuthState
import com.iscoding.myinstegramclone.domain.use_cases.authusercases.FirebaseSignIn
import com.iscoding.myinstegramclone.domain.use_cases.authusercases.FirebaseSignOut
import com.iscoding.myinstegramclone.domain.use_cases.authusercases.FirebaseSignUp
import com.iscoding.myinstegramclone.domain.use_cases.authusercases.IsUserAuthenticated
import com.iscoding.myinstegramclone.domain.use_cases.postUseCases.GetAllPosts
import com.iscoding.myinstegramclone.domain.use_cases.postUseCases.PostsUseCase
import com.iscoding.myinstegramclone.domain.use_cases.postUseCases.UploadPost
import com.iscoding.myinstegramclone.domain.use_cases.userusercases.UserUseCases
import com.iscoding.myinstegramclone.domain.use_cases.userusercases.GetUserDetails
import com.iscoding.myinstegramclone.domain.use_cases.userusercases.SetUserDetails
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InstagramModule {
    @Singleton
    @Provides
    fun providesFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Singleton
    @Provides
    fun ProvidesFirebaseFireStore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Singleton
    @Provides
    fun providesFirebaseStorage(): FirebaseStorage {
        return FirebaseStorage.getInstance()
    }

    @Singleton
    @Provides
    fun providesAuthenticationReposetory(
        auth: FirebaseAuth,
        firestore: FirebaseFirestore
    ): AuthenticationRepository {
        return AuthenticationRepositoryImp(auth, firestore)
    }

    @Singleton
    @Provides
    fun provideAuthUseCases(repoImp: AuthenticationRepository) = AuthenticationUseCases(
        isUserAuthenticated = IsUserAuthenticated(repoImp),
        firebaseSignUp = FirebaseSignUp(repoImp),
        firebaseSignIn = FirebaseSignIn(repoImp),
        firebaseSignOut = FirebaseSignOut(repoImp),
        firebaseState = FirebaseAuthState(repoImp)
    )

    @Singleton
    @Provides
    fun provideUserRepository(firestore: FirebaseFirestore): UserRepository {
        return UserRepositoryImp(firestore)
    }

    @Singleton
    @Provides
    fun provideUserUseCases(repository: UserRepository) = UserUseCases(
        getUserDetails = GetUserDetails(repository),
        setUserDetails = SetUserDetails(repository)
    )

    @Singleton
    @Provides
    fun providePostRepository(firestore: FirebaseFirestore): postRepository {
        return postRepositoryImp(fireBaseFireStore = firestore)
    }

    @Singleton
    @Provides
    fun providePostUseCase (postRepository: postRepository ) = PostsUseCase(
        getAllPosts = GetAllPosts(postRepository),
        uploadPost = UploadPost(postRepository)
    )

}