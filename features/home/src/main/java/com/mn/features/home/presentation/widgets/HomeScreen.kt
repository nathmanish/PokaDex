package com.mn.features.home.presentation.widgets

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.mn.features.home.presentation.activity.HomeViewModel

@Composable
fun HomeScreen() {

    val homeViewModel = hiltViewModel<HomeViewModel>()

    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            val pokeListPagingData = homeViewModel.pokeListPagingData.collectAsLazyPagingItems()

            val context = LocalContext.current
            LaunchedEffect(pokeListPagingData.loadState) {
                if (pokeListPagingData.loadState.refresh is LoadState.Error) {
                    Toast.makeText(
                        context,
                        (pokeListPagingData.loadState.refresh as LoadState.Error).error.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            if (pokeListPagingData.loadState.refresh is LoadState.Loading) {
                CircularProgressIndicator()
            } else {
                PokeGridWidget(
                    pokeListPagingData
                )
            }

        }
    }
}