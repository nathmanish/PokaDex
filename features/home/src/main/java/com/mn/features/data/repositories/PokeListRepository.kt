package com.mn.features.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mn.features.data.PokeApiService
import com.mn.features.data.mediator.PokeListMediator
import com.mn.features.data.models.PokeResponseModel
import kotlinx.coroutines.flow.Flow

interface PokeListRepository {
    fun getPokeList(): Flow<PagingData<PokeResponseModel>>
}

class PokeListRepositoryImpl(
    private val pokeListMediator: PokeListMediator
) : PokeListRepository {
    override fun getPokeList(): Flow<PagingData<PokeResponseModel>> {
        return Pager(
            config = PagingConfig(
                initialLoadSize = 20,
                pageSize = 20
            ),
            pagingSourceFactory = {
                pokeListMediator
            }
        ).flow
    }

}