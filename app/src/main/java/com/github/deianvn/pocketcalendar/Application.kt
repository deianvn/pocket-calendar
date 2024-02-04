package com.github.deianvn.pocketcalendar

import android.app.Application
import com.github.deianvn.pocketcalendar.di.BusinessModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@Application)
            modules(BusinessModule)
        }
    }
}