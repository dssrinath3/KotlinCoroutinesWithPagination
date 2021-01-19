package com.example.kotlincoroutinespagination.pagingdetails.model

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Data(
    @Json(name = "avatar")
   // @SerializedName("avatar")
    val avatar: String,
//    @SerializedName("email")
    @Json(name = "email")
    val email: String,
//    @SerializedName("first_name")
    @Json(name = "first_name")
    val firstName: String,
//    @SerializedName("id")
    val id: Int,
//    @SerializedName("last_name")
    @Json(name = "last_name")
    val lastName: String
)
