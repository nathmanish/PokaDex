package com.mn.features.domain.models

import com.mn.features.data.models.PokeResponseModel

data class PokeDataModel(
    var id: Int = 1,
    val name: String,
    val url: String
)