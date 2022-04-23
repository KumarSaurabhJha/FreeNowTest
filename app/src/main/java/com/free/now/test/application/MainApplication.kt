package com.free.now.test.application

import android.app.Application
import com.free.now.test.di.domainModule
import com.free.now.test.di.restApiModule
import com.free.now.test.di.restRepositoryModule
import com.free.now.test.di.retrofitModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MainApplication)
            modules(
                restRepositoryModule,
                restApiModule,
                domainModule,
                retrofitModule
            )
        }
    }
}