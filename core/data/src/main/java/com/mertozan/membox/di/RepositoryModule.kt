package com.mertozan.membox.di

import com.mertozan.membox.repository.MemRepositoryImpl
import com.mertozan.membox.repository.UserRepositoryImpl
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
    abstract fun bindMemRepository(memRepositoryImpl: MemRepositoryImpl): com.mertozan.membox.domain.repository.MemRepository

    @Binds
    @Singleton
    abstract fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): com.mertozan.membox.domain.repository.UserRepository

}