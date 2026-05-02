package com.mn.features.home.presentation.activity

import com.mn.core.architecture.presentation.models.FailureData
import com.mn.features.home.domain.models.PokeDataModel

sealed interface HomeUiState {
    data object Idle : HomeUiState

    data object Loading : HomeUiState

    data class Success(val items: List<PokeDataModel>) : HomeUiState

    data class Failure(val failureData: FailureData) : HomeUiState
}