package com.example.longdroid

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.tbuonomo.viewpagerdotsindicator.BuildConfig
import timber.log.Timber

class LongBlackApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}
