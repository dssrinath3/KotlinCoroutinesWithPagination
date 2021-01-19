package com.example.kotlincoroutinespagination.pagingdetails.network

import com.example.kotlincoroutinespagination.BuildConfig
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Call
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideHttpLoggerIntercepetor() :HttpLoggingInterceptor{
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Singleton
    @Provides
    fun provideTMDBApikeyIntercepetor(@Named("tmdb_api_key") apikey:String) :Interceptor{
        return Interceptor.invoke { chain ->
            val originalRequest = chain.request()
            val originalUrl = originalRequest.url

            val newUrl = originalUrl.newBuilder().addQueryParameter("api_key",apikey).build()
            val newRequest = originalRequest.newBuilder()
                .url(newUrl)
                .build()

            chain.proceed(newRequest)
        }
    }

    @Singleton
    @Provides
    fun provideCallFactory(httpLoggingInterceptor: HttpLoggingInterceptor
                           ,tmdbApikeyInterceptor: Interceptor) : Call.Factory{
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(tmdbApikeyInterceptor).build()
    }

    @Singleton
    @Provides
    fun  provideMoshi() :Moshi{
        return Moshi.Builder().build()
    }

    @Singleton
    @Provides
    fun  provideMoshiConverterFactory(moshi: Moshi) :MoshiConverterFactory{
        return MoshiConverterFactory.create(moshi)
    }


    @Singleton
    @Provides
    @Named("tmdb_base_url")
    fun  provideBaseUrl() :String{
        return BuildConfig.TMDB_BASE_URL
    }

    @Singleton
    @Provides
    @Named("tmdb_api_key")
    fun  provideTMDBApiKey() :String{
        return BuildConfig.TMDB_API_KEY
    }

    @Singleton
    @Provides
    fun  provideRetrofit(httpLoggingInterceptor: Call.Factory,
                         moshiConverterFactory: MoshiConverterFactory,
                         @Named("tmdb_base_url") baseUrl: String
    ) : Retrofit {
        //https://reqres.in/
        return Retrofit.Builder()
            .callFactory(httpLoggingInterceptor)
        //moshiConverterFactory
               // .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(moshiConverterFactory)
            .baseUrl(baseUrl).build()
    }

    @Singleton
    @Provides
    fun  provideMovieService(retrofit: Retrofit) :APIService{
        return retrofit.create(APIService::class.java)
    }

}