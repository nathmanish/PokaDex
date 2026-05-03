package com.mn.features.data.repositories

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mn.features.data.PokeApiService
import com.mn.features.data.database.AppDatabase
import com.mn.features.data.database.entities.PokeEntity
import com.mn.features.data.mediator.PokeRemoteMediator
import com.mn.features.data.models.PokeResponseModel
import kotlinx.coroutines.flow.Flow

interface PokeListRepository {
    fun getPokeList(): Flow<PagingData<PokeEntity>>
}

@OptIn(ExperimentalPagingApi::class)
class PokeListRepositoryImpl(
    private val pokeRemoteMediator: PokeRemoteMediator,
    private val appDatabase: AppDatabase
) : PokeListRepository {
    override fun getPokeList(): Flow<PagingData<PokeEntity>> {
        return Pager(
            config = PagingConfig(
                initialLoadSize = 20,
                pageSize = 20
            ),
            remoteMediator = pokeRemoteMediator,
            pagingSourceFactory = {
                appDatabase.pokeDao().pagingSource()
            }
        ).flow
    }

}