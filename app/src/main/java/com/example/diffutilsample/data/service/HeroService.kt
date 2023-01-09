package com.example.diffutilsample.data.service

import com.example.diffutilsample.data.dto.comicsinfo.ComicsWrapperResponse
import com.example.diffutilsample.data.dto.heroinfo.HeroInfoResponse
import com.example.diffutilsample.data.dto.heroinfo.HeroResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url


interface HeroService {
    @GET("v1/public/characters")
    suspend fun getHeroesAsync(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0,
    ): HeroResponseDto
}
