package com.example.diffutilsample.data.repository

import com.example.diffutilsample.data.dto.GreatResult
import com.example.diffutilsample.data.dto.comicsinfo.ComicsDto
import com.example.diffutilsample.data.dto.comicsinfo.ComicsWrapperDto
import com.example.diffutilsample.data.service.HeroService
import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class ComicsRepository @Inject constructor(
    private val service: HeroService
)