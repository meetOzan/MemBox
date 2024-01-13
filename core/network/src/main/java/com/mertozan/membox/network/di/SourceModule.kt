package com.mertozan.membox.network.di

import com.mertozan.membox.network.firestore.FirebaseSource
import com.mertozan.membox.network.firestore.FirebaseSourceImpl
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