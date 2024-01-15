package com.mertozan.membox.core.di

import android.content.Context
import com.mertozan.membox.core.resource.ResourceProvider
import com.mertozan.membox.core.resource.ResourceProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InfrastructureModule {

    @Provides
    @Singleton
    fun provideStringResourceProvider(
        @ApplicationContext context: Context
    ): ResourceProvider = ResourceProviderImpl(context = context)

}