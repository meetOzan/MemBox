package com.mertozan.membox.network.di

import com.mertozan.membox.network.firebase.FirebaseSource
import com.mertozan.membox.network.firebase.FirebaseSourceImpl
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
    abstract fun bindFirebaseSource(firebaseSourceImpl: FirebaseSourceImpl): FirebaseSource

}