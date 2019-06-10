package com.iambedant.mvistarter.data.remote.model

import com.google.gson.annotations.SerializedName

data class Comments
(

    @field:SerializedName("postId")
    val postId: Int? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("body")
    val body1: String? = null
)