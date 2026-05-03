@file:OptIn(ExperimentalPagingApi::class)

package com.mn.features.data.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import coil3.network.HttpException
import com.mn.features.data.PokeApiService
import com.mn.features.data.database.AppDatabase
import com.mn.features.data.database.entities.PokeEntity
import com.mn.features.data.models.toPokeEntity
import java.io.IOException

class PokeRemoteMediator(
    private val pokeApiService: PokeApiService,
    private val pokeDb: AppDatabase
) : RemoteMediator<Int, PokeEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokeEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 0
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )

                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    lastItem?.id ?: 1
                }
            }

            val response = pokeApiService.getPokeList(
                page = loadKey,
                perPage = state.config.pageSize
            )

            val pokeList = response.body()?.pokeList ?: emptyList()
            pokeDb.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    pokeDb.pokeDao().clearAll()
                }

                val pokeEntities = pokeList.map {
                    it.toPokeEntity()
                }
                pokeDb.pokeDao().insertAll(pokeEntities)
            }

            MediatorResult.Success(
                endOfPaginationReached = pokeList.isEmpty()
            )

        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

}