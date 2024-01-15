package com.mertozan.membox.source.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SourceModule {

    @Binds
    @Singleton
    abstract fun bindFirebaseSource(firebaseSourceImpl: com.mertozan.membox.source.local.FirebaseSourceImpl): com.mertozan.membox.source.local.FirebaseSource

}