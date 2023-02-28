package com.example.diffutilsample.data.repository

import com.example.diffutilsample.data.dto.GreatResult
import com.example.diffutilsample.data.dto.comicsinfo.ComicsWrapperDto
import com.example.diffutilsample.data.dto.heroinfo.HeroInfoDto
import com.example.diffutilsample.data.dto.heroinfo.HeroResponseDto
import com.example.diffutilsample.data.service.HeroService
import com.example.diffutilsample.room.HeroesDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class HeroRepository @Inject constructor(
    private val heroService: HeroService,
    private val heroesDao: HeroesDao

) {
    suspend fun loadHeroes(limit: Int, offset: Int): HeroResponseDto {
        return withContext(Dispatchers.IO) {
            heroService.getHeroesAsync(limit, offset)
        }
    }

    suspend fun loadHeroInfoById(heroId: Long): GreatResult<HeroInfoDto> {
        return GreatResult.Success(
            heroService.getHeroInfo(heroId = heroId).info.results.first()
        )
    }

    suspend fun loadComicsById(id: String): GreatResult<ComicsWrapperDto> {
        return GreatResult.Success(heroService.getComicsInfoById(id).info)
    }
}