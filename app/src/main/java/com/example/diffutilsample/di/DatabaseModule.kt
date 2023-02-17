package com.example.diffutilsample.di

import android.content.Context
import androidx.room.Room
import com.example.diffutilsample.room.AppDatabase
import com.example.diffutilsample.room.HeroesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {


    @Provides
    fun provideChannelDao(appDatabase: AppDatabase): HeroesDao {
        return appDatabase.heroesDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "1"
        ).build()
    }
}