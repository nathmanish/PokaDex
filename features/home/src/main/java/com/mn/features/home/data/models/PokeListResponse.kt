package com.mn.features.home.data.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class PokeListResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    @SerializedName("results")
    val pokeList: List<PokeResponseModal>?
)

data class PokeResponseModal(
    val name: String,
    val url: String?
)