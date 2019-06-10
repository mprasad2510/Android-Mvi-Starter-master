package com.iambedant.mvistarter.di.module

import com.iambedant.mvistarter.data.Repository
import com.iambedant.mvistarter.data.RepositoryImpl
import com.iambedant.mvistarter.data.remote.Network
import dagger.Module
import dagger.Provides


@Module
class DataModule {
    @Provides
    fun provideRepository(service: Network): Repository {
        return RepositoryImpl(service)
    }
}