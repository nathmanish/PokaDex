package com.mn.features.home.data

import com.mn.core.architecture.data.models.ErrorResponse
import com.mn.core.architecture.data.models.ResponseResult
import com.mn.features.home.data.models.PokeResponseModal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.net.UnknownHostException

interface PokeListRemoteRepository {
    fun getPokeList(): Flow<ResponseResult<List<PokeResponseModal>>>
}

internal class PokeListRemoteRepositoryImpl(
    val apiService: PokeApiService
) : PokeListRemoteRepository {
    override fun getPokeList(): Flow<ResponseResult<List<PokeResponseModal>>> = flow {
        val response = apiService.getPokeList()
        val result = response.body()
        if (response.isSuccessful && result != null && !result.pokeList.isNullOrEmpty()) {
            emit(ResponseResult.Success(result.pokeList))
        } else {
            emit(ResponseResult.Failure(ErrorResponse.NoDataFound))
        }
    }.catch { t ->
        if (t is UnknownHostException) {
            emit(ResponseResult.Failure(ErrorResponse.NoInternet))
        } else {
            emit(ResponseResult.Failure(ErrorResponse.NoDataFound))
        }
    }.flowOn(Dispatchers.IO)
}