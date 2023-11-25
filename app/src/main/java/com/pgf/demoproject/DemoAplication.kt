package com.pgf.demoproject

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module

class DemoAplication: Application() {

    val appModule = module {
        single { UserRepositoryImpl() }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@DemoAplication)
            modules(appModule)
        }
    }
}