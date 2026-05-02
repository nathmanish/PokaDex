package com.mn.features.home.di

import com.mn.core.architecture.domain.mapper.ErrorResponseMapper
import com.mn.core.architecture.presentation.mapper.FailureDataMapper
import com.mn.core.networkclient.NetworkClient
import com.mn.features.home.data.PokeApiService
import com.mn.features.home.data.PokeListRemoteRepository
import com.mn.features.home.data.PokeListRemoteRepositoryImpl
import com.mn.features.home.domain.mappers.PokeListMapper
import com.mn.features.home.domain.mappers.PokeListMapperImpl
import com.mn.features.home.domain.usecases.PokeListUseCase
import com.mn.features.home.domain.usecases.PokeListUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object HomeModule {

    @Provides
    @ViewModelScoped
    fun providesPokeApiService(networkClient: NetworkClient) =
        networkClient.create(PokeApiService::class.java)

    @Provides
    @ViewModelScoped
    fun providesPokeListRemoteRepository(pokeApiService: PokeApiService): PokeListRemoteRepository =
        PokeListRemoteRepositoryImpl(pokeApiService)

    @Provides
    @ViewModelScoped
    fun providesPokeListMapper(): PokeListMapper = PokeListMapperImpl()

    @Provides
    @ViewModelScoped
    fun providesPokeListUseCase(
        pokeListRemoteRepository: PokeListRemoteRepository,
        pokeListMapper: PokeListMapper,
        errorResponseMapper: ErrorResponseMapper
    ): PokeListUseCase =
        PokeListUseCaseImpl(
            pokeListRemoteRepository,
            pokeListMapper,
            { errorResponseMapper }
        )

    @Provides
    @ViewModelScoped
    fun providesFailureDataMapper(): FailureDataMapper = FailureDataMapper()
}