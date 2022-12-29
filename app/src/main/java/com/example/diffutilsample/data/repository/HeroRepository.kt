package com.example.diffutilsample.data.repository

import com.example.diffutilsample.data.dto.GreatResult
import com.example.diffutilsample.data.dto.heroinfo.HeroResponseDto
import com.example.diffutilsample.data.service.HeroService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HeroRepository @Inject constructor(
    private val heroService: HeroService
) {

    suspend fun loadHeroes(): GreatResult<HeroResponseDto> {
        return GreatResult.Success(heroService.getHeroesAsync())
    }
}