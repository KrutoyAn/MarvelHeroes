package com.example.diffutilsample.presentation.viewmodel

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import com.example.diffutilsample.data.dto.GreatResult
import com.example.diffutilsample.data.dto.heroinfo.HeroResponseDto
import com.example.diffutilsample.data.repository.HeroRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HeroesViewModel
@Inject constructor(
    private var heroRepository: HeroRepository
) : ViewModel(), LifecycleObserver {

    suspend fun fetchHeroes(): GreatResult<HeroResponseDto> {
        return try {
            heroRepository.loadHeroes()
        } catch (exception: Exception) {
            GreatResult.Error(exception)
        }
    }
}