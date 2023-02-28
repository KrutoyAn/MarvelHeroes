package com.example.diffutilsample.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.diffutilsample.data.dto.heroinfo.HeroEntity



@Dao
interface HeroesDao {
    @Query("SELECT * FROM heroentity")
    suspend fun getAll(): List<HeroEntity>

    @Insert
    fun insert(heroes: List<HeroEntity>)
}