package com.example.diffutilsample.presentation.fragments

import androidx.lifecycle.ViewModel
import com.example.diffutilsample.data.dto.GreatResult
import com.example.diffutilsample.data.dto.comicsinfo.ComicsWrapperDto
import com.example.diffutilsample.data.dto.heroinfo.HeroInfoDto
import com.example.diffutilsample.data.repository.HeroRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FragmentHeroesViewModel
@Inject constructor(
    private val heroRepository: HeroRepository
) : ViewModel() {

    suspend fun fetchHeroInfo(heroId: Long): GreatResult<HeroInfoDto> {
        return try {
            heroRepository.loadHeroInfoById(heroId)
        } catch (exception: Exception) {
            GreatResult.Error(exception)
        }
    }

    suspend fun fetchComicsInfoById(id: String): GreatResult<ComicsWrapperDto> {
        return try {
            heroRepository.loadComicsById(id)
        } catch (exception: Exception) {
            GreatResult.Error(exception)
        }
    }
}