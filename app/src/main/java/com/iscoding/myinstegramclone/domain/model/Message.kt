package com.iscoding.myinstegramclone.domain.model

data class Message(
    val text: String,
    val authorId: String
) {
    open fun isFromMe(curId:String)= authorId == curId
}
