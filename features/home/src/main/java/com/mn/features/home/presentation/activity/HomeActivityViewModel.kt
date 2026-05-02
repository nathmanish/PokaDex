package com.mn.features.home.presentation.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mn.core.architecture.domain.models.DataResult
import com.mn.core.architecture.presentation.mapper.FailureDataMapper
import com.mn.core.architecture.presentation.models.FailureData
import com.mn.features.home.domain.usecases.PokeListUseCase
import dagger.Lazy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeActivityViewModel @Inject constructor(
    private val pokeListUseCase: PokeListUseCase,
    private val failureDataMapper: Lazy<FailureDataMapper>
) : ViewModel() {
    private val _homeUiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState.Idle)
    val homeUiState = _homeUiState.asStateFlow()

    init {
        getPokeList()
    }

    private fun getPokeList() {
        _homeUiState.value = HomeUiState.Loading
        viewModelScope.launch {
            pokeListUseCase().collectLatest { it ->
                if (it is DataResult.Success) {
                    _homeUiState.value = HomeUiState.Success(it.data)
                }
                if (it is DataResult.Failure) {
                    _homeUiState.value = HomeUiState.Failure(
                        failureDataMapper.get().invoke(it.error)
                    )
                }
            }
        }
    }
}