package com.mn.features.home.domain.mappers

import com.mn.core.architecture.domain.mapper.Mapper
import com.mn.features.home.data.models.PokeResponseModal
import com.mn.features.home.domain.models.PokeDataModel

interface PokeListMapper : Mapper<List<PokeResponseModal>, List<PokeDataModel>>

internal class PokeListMapperImpl : PokeListMapper {
    override fun invoke(from: List<PokeResponseModal>): List<PokeDataModel> {
        return from.map {
            PokeDataModel(
                name = it.name,
                url = it.url
            )
        }
    }

}