package com.example.kotlincoroutinespagination.pagingdetails

import android.app.Application
import com.example.kotlincoroutinespagination.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class KotlinPaginationApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG)
        {
            Timber.plant(Timber.DebugTree())
        }
    }
}