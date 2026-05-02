package com.mn.features.domain.usecases

import androidx.paging.PagingData
import com.mn.core.architecture.domain.usecase.UseCase
import com.mn.features.data.repositories.PokeListRepository
import com.mn.features.domain.mappers.PokeListMapper
import com.mn.features.domain.models.PokeDataModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface PokeListUseCase : UseCase<Flow<PagingData<PokeDataModel>>>

class PokeListUseCaseImpl(
    private val pokeListRepository: PokeListRepository,
    private val pokeListMapper: PokeListMapper
) : PokeListUseCase {
    override fun invoke(): Flow<PagingData<PokeDataModel>> {
        return pokeListRepository.getPokeList().map {
            pokeListMapper.invoke(it)
        }
    }
}