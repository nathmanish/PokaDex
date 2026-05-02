package com.mn.features.home.presentation.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.mn.features.home.presentation.activity.HomeViewModel

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            val pokeListPagingData = homeViewModel.pokeListPagingData.collectAsLazyPagingItems()

            when (pokeListPagingData.loadState.refresh) {
                is LoadState.Loading -> {
                    CircularProgressIndicator()
                }

                is LoadState.Error -> {
                    Text("Something went wrong")
                }

                is LoadState.NotLoading -> {
                    if (!pokeListPagingData.itemSnapshotList.isEmpty()) {
                        PokeGridWidget(
                            pokeListPagingData
                        )
                    } else {
                        Text("No Data found")
                    }
                }
            }
        }
    }
}