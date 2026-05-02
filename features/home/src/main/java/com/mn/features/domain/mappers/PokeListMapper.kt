package com.mn.features.domain.mappers

import androidx.paging.PagingData
import androidx.paging.map
import com.mn.core.architecture.domain.mapper.Mapper
import com.mn.features.data.models.PokeResponseModel
import com.mn.features.domain.models.PokeDataModel

class PokeListMapper : Mapper<PagingData<PokeResponseModel>, PagingData<PokeDataModel>> {
    override fun invoke(from: PagingData<PokeResponseModel>): PagingData<PokeDataModel> {
        return from.map {
            PokeDataModel(
                name = it.name,
                url = it.url
            )
        }
    }
}