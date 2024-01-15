package com.mertozan.membox.source.di

import android.content.Context
import androidx.room.Room
import com.mertozan.membox.source.local.dao.MemoryDao
import com.mertozan.membox.source.local.dao.UserDao
import com.mertozan.membox.source.local.database.MemDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MemDatabase {
        return Room.databaseBuilder(
            context,
            MemDatabase::class.java,
            DATABASE_NAME
        ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideUserDao(memDatabase: MemDatabase): UserDao {
        return memDatabase.userDao()
    }

    @Provides
    @Singleton
    fun provideMemoryDao(memDatabase: MemDatabase): MemoryDao {
        return memDatabase.memoryDao()
    }

    private const val DATABASE_NAME = "mem_database.db"
}