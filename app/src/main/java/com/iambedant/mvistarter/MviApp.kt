package com.iambedant.mvistarter

import com.iambedant.mvistarter.di.component.DaggerAppComponent
import com.iambedant.mvistarter.di.module.AppModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber


class MviApp : DaggerApplication(){
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}