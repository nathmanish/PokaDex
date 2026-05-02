package com.mn.features.home.presentation.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.mn.features.domain.usecases.PokeListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val pokeListUseCase: PokeListUseCase
) : ViewModel() {
    val pokeListPagingData = pokeListUseCase.invoke().cachedIn(viewModelScope)
}