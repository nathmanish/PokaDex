package com.mn.features.di

import com.mn.core.networkclient.NetworkClient
import com.mn.features.data.PokeApiService
import com.mn.features.data.database.AppDatabase
import com.mn.features.data.mediator.PokeRemoteMediator
import com.mn.features.data.repositories.PokeListRepository
import com.mn.features.data.repositories.PokeListRepositoryImpl
import com.mn.features.domain.usecases.PokeListUseCase
import com.mn.features.domain.usecases.PokeListUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class HomeModule {

    @Provides
    @ViewModelScoped
    fun providesApiService(networkClient: NetworkClient): PokeApiService = networkClient.create(
        PokeApiService::class.java
    )

    @Provides
    @ViewModelScoped
    fun providesPokeListRepository(
        pokeRemoteMediator: PokeRemoteMediator,
        appDatabase: AppDatabase
    ): PokeListRepository =
        PokeListRepositoryImpl(pokeRemoteMediator, appDatabase)

    @Provides
    @ViewModelScoped
    fun providesPokeListUseCase(
        pokeListRepository: PokeListRepository,
    ): PokeListUseCase =
        PokeListUseCaseImpl(pokeListRepository)

    @Provides
    @ViewModelScoped
    fun providesPokeRemoteMediator(
        pokeApiService: PokeApiService,
        pokeDatabase: AppDatabase
    ): PokeRemoteMediator = PokeRemoteMediator(
        pokeApiService,
        pokeDatabase
    )

}