package com.mn.features.home.domain.usecases

import com.mn.core.architecture.data.models.ResponseResult
import com.mn.core.architecture.domain.mapper.ErrorResponseMapper
import com.mn.core.architecture.domain.models.DataResult
import com.mn.core.architecture.domain.models.FailureResult
import com.mn.core.architecture.domain.usecase.UseCase
import com.mn.features.home.data.PokeListRemoteRepository
import com.mn.features.home.data.models.PokeResponseModal
import com.mn.features.home.domain.mappers.PokeListMapper
import com.mn.features.home.domain.models.PokeDataModel
import dagger.Lazy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

interface PokeListUseCase : UseCase<Flow<DataResult<List<PokeDataModel>>>>

internal class PokeListUseCaseImpl(
    private val pokeListRemoteRepository: PokeListRemoteRepository,
    private val pokeListMapper: PokeListMapper,
    private val errorResponseMapper: Lazy<ErrorResponseMapper>
) : PokeListUseCase {
    override fun invoke(): Flow<DataResult<List<PokeDataModel>>> {
        return pokeListRemoteRepository.getPokeList().map { it ->
            mapResponseResult(it)
        }.catch {
            emit(DataResult.Failure(FailureResult.UnknownError))
        }.flowOn(Dispatchers.Default)
    }

    private fun mapResponseResult(
        responseResult: ResponseResult<List<PokeResponseModal>>
    ) = when (responseResult) {
        is ResponseResult.Success -> {
            val pokeList = responseResult.data
            DataResult.Success(pokeListMapper.invoke(pokeList))
        }

        is ResponseResult.Failure -> {
            DataResult.Failure(errorResponseMapper.get().invoke(responseResult.error))
        }
    }

}