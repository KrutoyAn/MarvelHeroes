package com.example.diffutilsample

import androidx.compose.ui.Modifier.Companion.any
import com.example.diffutilsample.data.dto.GreatResult
import com.example.diffutilsample.data.dto.comicsinfo.ComicsWrapperDto
import com.example.diffutilsample.data.dto.comicsinfo.ComicsWrapperResponse
import com.example.diffutilsample.data.repository.HeroRepository
import com.example.diffutilsample.data.service.HeroService
import com.example.diffutilsample.room.HeroesDao
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(MockitoJUnitRunner::class)
class HeroRepositoryTest {

    private val expectedResponse = ComicsWrapperResponse(
        ComicsWrapperDto(results = emptyList())
    )
    private val heroService = mockk<HeroService>()
    private val heroesDao = mockk<HeroesDao>()

    private val repository: HeroRepository = HeroRepository(heroService, heroesDao)

    @org.junit.Test
    fun fetchComicsInfoByIdTest() = runBlocking {
        //given
        val expectedId = "123"
        coEvery { heroService.getComicsInfoById(any()) } returns expectedResponse
        // when
        val result = repository.loadComicsById(expectedId)
        //then
        coVerify { heroService.getComicsInfoById(expectedId) }
        Assertions.assertEquals((result as GreatResult.Success).data, expectedResponse.info)
    }
}