package com.example.ceep

import android.app.Application
import com.example.ceep.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NotesApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin{
            modules(listOf(appModule))
            androidContext(this@NotesApplication)
        }
    }


}