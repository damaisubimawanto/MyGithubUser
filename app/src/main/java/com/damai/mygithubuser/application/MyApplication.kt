package com.damai.mygithubuser.application

import android.app.Application
import com.damai.mygithubuser.module.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

/**
 * Created by damai.subimawanto on 2/18/2022.
 */
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    mapperModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}