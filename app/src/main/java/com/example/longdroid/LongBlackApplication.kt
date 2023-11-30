package com.example.longdroid

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.example.longdroid.data.datasource.LongBlackStorage
import com.tbuonomo.viewpagerdotsindicator.BuildConfig
import timber.log.Timber

class LongBlackApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        LongBlackStorage.init(this)
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}
