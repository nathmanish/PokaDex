package com.mn.features.home.presentation.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mn.features.home.presentation.activity.HomeActivityViewModel
import com.mn.features.home.presentation.activity.HomeUiState

@Composable
fun HomeScreen(
    vm: HomeActivityViewModel = hiltViewModel()
) {
    Scaffold(modifier = Modifier.fillMaxWidth()) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
        ) {
            val uiState by vm.homeUiState.collectAsStateWithLifecycle()
            when (uiState) {
                HomeUiState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is HomeUiState.Success -> {
                    PokeListWidget(
                        modifier = Modifier,
                        (uiState as HomeUiState.Success).items
                    )
                }

                is HomeUiState.Failure -> {

                }

                else -> {}

            }
        }
    }
}