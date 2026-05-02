package com.mn.features.data.mediator

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mn.features.data.PokeApiService
import com.mn.features.data.models.PokeResponseModel

class PokeListMediator(
    val apiService: PokeApiService
) : PagingSource<Long, PokeResponseModel>() {
    override suspend fun load(params: LoadParams<Long>): LoadResult<Long, PokeResponseModel> {
        return try {
            val page = params.key ?: 0L
            val limit = params.loadSize
            val response = apiService.getPokeList(
                page = page,
                perPage = limit
            )
            val result = response.body()
            if (response.isSuccessful && result != null && !result.pokeList.isEmpty()) {
                LoadResult.Page(
                    data = result.pokeList,
                    prevKey = null,
                    nextKey = page + limit
                )
            } else {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (t: Throwable) {
            LoadResult.Error(t)
        }
    }

    override fun getRefreshKey(state: PagingState<Long, PokeResponseModel>): Long? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.nextKey
                ?: state.closestPageToPosition(position)?.prevKey
        }
    }
}