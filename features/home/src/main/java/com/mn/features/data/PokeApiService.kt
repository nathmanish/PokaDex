package com.mn.features.data

import com.mn.features.data.models.PokeListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PokeApiService {
    @GET(".")
    suspend fun getPokeList(
        @Query("offset") page: Int,
        @Query("limit") perPage: Int
    ): Response<PokeListResponse>
}