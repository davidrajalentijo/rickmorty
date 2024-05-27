package com.draja.rickmorty

import android.app.Application
import com.draja.network.NetworkManager
import com.draja.rickmorty.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger(Level.ERROR)
            androidContext(this@MainApplication)
            modules(appModule)
        }

        NetworkManager.createInstance()
    }
}