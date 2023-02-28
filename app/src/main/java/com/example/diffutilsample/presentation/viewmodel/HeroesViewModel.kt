package com.example.diffutilsample.presentation.viewmodel

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.diffutilsample.data.dto.heroinfo.HeroResponse
import com.example.diffutilsample.data.repository.HeroRepository
import com.example.diffutilsample.data.source.HeroDataSource
import com.example.diffutilsample.presentation.model.HeroModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class HeroesViewModel
@Inject constructor(
    private var heroRepository: HeroRepository
) : ViewModel(), LifecycleObserver {

    val heroes: Flow<PagingData<HeroResponse>> = Pager(
        PagingConfig(pageSize = 20)
    ) {
        HeroDataSource(heroRepository)
    }.flow.cachedIn(viewModelScope)

    var heroesList = mutableListOf<HeroModel>()

    /*suspend fun fetchHeroes(): GreatResult<List<HeroModel>> {
        return heroRepository.loadHeroes()
    }*/
}