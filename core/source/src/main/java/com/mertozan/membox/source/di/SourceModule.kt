package com.mertozan.membox.source.di

import com.mertozan.membox.domain.source.LocalSource
import com.mertozan.membox.source.local.LocalSourceImpl
import com.mertozan.membox.domain.source.FirebaseSource
import com.mertozan.membox.source.network.FirebaseSourceImpl
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
    abstract fun bindFirebaseSource(firebaseSourceImpl: FirebaseSourceImpl): com.mertozan.membox.domain.source.FirebaseSource

    @Binds
    @Singleton
    abstract fun bindLocalSource(localSourceImpl: LocalSourceImpl): com.mertozan.membox.domain.source.LocalSource

}