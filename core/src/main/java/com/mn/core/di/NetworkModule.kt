package com.mn.core.di

import com.mn.core.architecture.data.models.ErrorResponse
import com.mn.core.architecture.domain.mapper.ErrorResponseMapper
import com.mn.core.architecture.domain.mapper.Mapper
import com.mn.core.architecture.domain.models.FailureResult
import com.mn.core.networkclient.NetworkClient
import com.mn.core.networkclient.NetworkClientImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesNetworkClientFactory(): NetworkClient = NetworkClientImpl()

    @Provides
    @Singleton
    fun providesErrorResponseMapper(): ErrorResponseMapper = ErrorResponseMapper()
}