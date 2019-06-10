package com.iambedant.mvistarter.di.module

import com.iambedant.mvistarter.di.ActivityScope
import com.iambedant.mvistarter.feature.home.HomeActionProcessorHolder
import com.iambedant.mvistarter.feature.home.HomeViewmodelFactory
import com.iambedant.mvistarter.feature.login.LoginActionProcessorHolder
import com.iambedant.mvistarter.feature.login.LoginViewmodelFactory
import com.iambedant.mvistarter.feature.user.UserActionProcessorHolder
import com.iambedant.mvistarter.feature.user.UserViewModelFactory
import dagger.Module
import dagger.Provides


@Module(includes = [DataModule::class])
class MviModule {

    @Provides
    @ActivityScope
    fun provideloginViewmodelFactory(actionProcessorHolder: LoginActionProcessorHolder): LoginViewmodelFactory {
        return LoginViewmodelFactory(actionProcessorHolder)
    }

    @Provides
    @ActivityScope
    fun provideHomeViewmodelFactory(actionProcessorHolder: HomeActionProcessorHolder): HomeViewmodelFactory {
        return HomeViewmodelFactory(actionProcessorHolder)
    }
    @Provides
    @ActivityScope
    fun provideUserViewmodelFactory(actionProcessorHolder: UserActionProcessorHolder): UserViewModelFactory {
        return UserViewModelFactory(actionProcessorHolder)
    }

}