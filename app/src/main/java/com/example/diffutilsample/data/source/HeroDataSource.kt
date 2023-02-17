package com.example.diffutilsample.data.source

import android.widget.ProgressBar
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.diffutilsample.data.dto.heroinfo.HeroResponse
import com.example.diffutilsample.data.repository.HeroRepository
import retrofit2.HttpException

class HeroDataSource constructor(
    private val repository: HeroRepository
) : PagingSource<Int, HeroResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, HeroResponse> {
        return try {
            val offset = params.key ?: 0
            val movieListResponse = repository.loadHeroes(limit = 20, offset = offset)
            LoadResult.Page(
                data = movieListResponse.pagingInfo.results,
                prevKey = if (offset == 0) null else offset - 20,
                nextKey = movieListResponse.pagingInfo.offset.plus(20)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }catch (httpE:HttpException){
            LoadResult.Error(httpE)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, HeroResponse>): Int {
        return 0
    }
}

/*
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
}*/
