package com.mertozan.membox.di

import com.mertozan.membox.MemRepository
import com.mertozan.membox.MemRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMemRepository(memRepositoryImpl: MemRepositoryImpl): MemRepository

}