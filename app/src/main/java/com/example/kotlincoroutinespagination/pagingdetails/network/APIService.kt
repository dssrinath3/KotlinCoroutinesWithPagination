package com.example.kotlincoroutinespagination.pagingdetails.network

import com.example.kotlincoroutinespagination.BuildConfig
import com.example.kotlincoroutinespagination.pagingdetails.model.ApiResponse
import com.example.kotlincoroutinespagination.pagingdetails.model.MovieResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface APIService {
    //api/users
    //MovieResponse
    //movie/popular
    @GET("movie/popular")
    suspend fun getListData(
        @Query("page") pageNumber: Int?
    ): MovieResponse

}