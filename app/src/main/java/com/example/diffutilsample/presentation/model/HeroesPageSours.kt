//package com.example.diffutilsample.presentation.model
//
//import androidx.paging.PagingSource
//import androidx.paging.PagingState
//import com.example.diffutilsample.data.service.HeroService
//import retrofit2.HttpException
//
//class HeroesPageSours(
//    private val heroService: HeroService,
//    private val query: String
//) :
//    PagingSource<Int, Article>() {
//
//
//    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
//        val anchorPosition = state.anchorPosition ?: return null
//        val page = state.closestPageToPosition(anchorPosition) ?: return null
//        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
//
//    }
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
//        if (query.isEmpty()) {
//            return LoadResult.Page(emptyList(), prevKey = null, nextKey = null)
//        }
//        val page: Int = params.key ?: 1
//        val pageSize: Int = params.loadSize
//
//        //val response = heroService.getHeroesAsync(query, page, pageSize)
//
//        if (response.isSuccessful) {
//            val articles = checkNotNull(response.body()).articles.map { it.toArticle() }
//            val nextKey = if (articles.size < pageSize) null else page + 1
//            val prevKey = if (page == 1) null else page - 1
//            return LoadResult.Page(articles, prevKey, nextKey)
//        } else {
//            return LoadResult.Error(HttpException(response))
//        }
//    }
//
//
//}
//
//
