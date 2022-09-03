package com.example.mvprxjava.imagelist

import com.example.mvprxjava.data.DataRepository
import com.example.mvprxjava.data.DataRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ImageListModule {

    @Singleton
    @Provides
    fun provideDataRepository(): DataRepository {
        return DataRepositoryImpl()
    }
}