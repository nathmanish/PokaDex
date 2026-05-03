package com.mn.features.domain.usecases

import androidx.paging.PagingData
import androidx.paging.map
import com.mn.core.architecture.domain.usecase.UseCase
import com.mn.features.data.repositories.PokeListRepository
import com.mn.features.domain.models.PokeDataModel
import com.mn.features.domain.models.toPokeDataModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface PokeListUseCase : UseCase<Flow<PagingData<PokeDataModel>>>

class PokeListUseCaseImpl(
    private val pokeListRepository: PokeListRepository
) : PokeListUseCase {
    override fun invoke(): Flow<PagingData<PokeDataModel>> {
        return pokeListRepository.getPokeList().map { pagingData ->
            pagingData.map {
                it.toPokeDataModel()
            }
        }
    }

}