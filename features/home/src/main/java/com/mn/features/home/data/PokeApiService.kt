package com.mn.features.home.data

import com.mn.features.home.data.models.PokeListResponse
import com.mn.features.home.data.models.PokeResponseModal
import retrofit2.Response
import retrofit2.http.GET

interface PokeApiService {
    @GET(".")
    suspend fun getPokeList(): Response<PokeListResponse>
}