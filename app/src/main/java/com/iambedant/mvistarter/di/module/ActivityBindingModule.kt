package com.iambedant.mvistarter.di.module

import com.iambedant.mvistarter.feature.home.HomeActivity
import com.iambedant.mvistarter.feature.login.LoginActivity
import com.iambedant.mvistarter.feature.user.UserActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {
    @ContributesAndroidInjector(modules = [(MviModule::class)])
    abstract fun loginActivity(): LoginActivity
    @ContributesAndroidInjector(modules = [(MviModule::class)])
    abstract fun homeActivity(): HomeActivity
    @ContributesAndroidInjector(modules = [(MviModule::class)])
    abstract fun userActivity(): UserActivity

}