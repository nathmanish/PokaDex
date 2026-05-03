package com.mn.features.domain.models

import com.mn.features.data.database.entities.PokeEntity
import com.mn.features.data.models.PokeResponseModel

data class PokeDataModel(
    var id: Int,
    val name: String,
    val url: String
)

fun PokeEntity.toPokeDataModel(): PokeDataModel {
    return PokeDataModel(
        this.id,
        this.name,
        this.url
    )
}