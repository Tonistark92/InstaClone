package com.iscoding.myinstegramclone.domain.repository

import com.iscoding.myinstegramclone.domain.model.User
import com.iscoding.myinstegramclone.util.MyResponse
import kotlinx.coroutines.flow.Flow

interface UserRepository  {
    fun getUserDetails (userId:String) : Flow<MyResponse<User>>
    fun setUserDetails (
        userid:String,
        name:String,
        userName:String,
        bio:String,
        siteUrl:String
    ):Flow<MyResponse<Boolean>>
}