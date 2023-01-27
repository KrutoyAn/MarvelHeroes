package com.example.diffutilsample.presentation.viewmodel

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import com.example.diffutilsample.data.dto.GreatResult
import com.example.diffutilsample.data.repository.HeroRepository
import com.example.diffutilsample.presentation.model.HeroModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HeroesViewModel
@Inject constructor(
    private var heroRepository: HeroRepository
) : ViewModel(), LifecycleObserver {

    suspend fun fetchHeroes(): GreatResult<List<HeroModel>> {
        return heroRepository.loadHeroes()
    }
}