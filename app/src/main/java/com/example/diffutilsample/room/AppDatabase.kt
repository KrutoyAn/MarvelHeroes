package com.example.diffutilsample.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.diffutilsample.data.dto.heroinfo.HeroEntity

@Database(entities = [HeroEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun heroesDao(): HeroesDao
}