package com.example.diffutilsample.data.repository

import com.example.diffutilsample.data.dto.GreatResult
import com.example.diffutilsample.data.dto.heroinfo.mapToEntity
import com.example.diffutilsample.data.dto.heroinfo.mapToModel
import com.example.diffutilsample.data.service.HeroService
import com.example.diffutilsample.presentation.model.HeroModel
import com.example.diffutilsample.room.HeroesDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class HeroRepository @Inject constructor(
    private val heroService: HeroService, private val heroesDao: HeroesDao
) {
    suspend fun loadHeroes(): GreatResult<List<HeroModel>> {
        return withContext(Dispatchers.IO) {
            try {
                val heroes =
                    heroService.getHeroesAsync().pagingInfo.results.map { it.mapToEntity() }
                heroesDao.insert(heroes)
                GreatResult.Success(heroes.map { it.mapToModel() })
            } catch (e: Exception) {
                val heroes = heroesDao.getAll()
                if (heroes.isEmpty()) {
                    GreatResult.Error(e)
                } else {
                    GreatResult.Success(heroes.map { it.mapToModel() })
                }
            }
        }
    }
}