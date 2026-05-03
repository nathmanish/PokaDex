package com.mn.features.data.models

import com.google.gson.annotations.SerializedName
import com.mn.features.data.database.entities.PokeEntity

data class PokeListResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    @SerializedName("results")
    val pokeList: List<PokeResponseModel>
)

data class PokeResponseModel(
    val name: String,
    val url: String
)

fun PokeResponseModel.toPokeEntity(): PokeEntity {
    return PokeEntity(
        name = name,
        url = url
    )
}