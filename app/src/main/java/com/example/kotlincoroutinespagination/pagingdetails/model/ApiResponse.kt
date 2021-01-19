package com.example.kotlincoroutinespagination.pagingdetails.model

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiResponse(
    @Json(name = "data")
//         @SerializedName("data")
    val myData: List<Data>,
    @Json(name = "page")
//        @SerializedName("page")
    val page: Int,
    @Json(name = "per_page")
//         @SerializedName("per_page")
    val per_page: Int,
//         @SerializedName("total")
    @Json(name = "total")
    val total: Int,
//         @SerializedName("total_pages")
         @Json(name = "total_pages")
    val total_pages: Int
)
