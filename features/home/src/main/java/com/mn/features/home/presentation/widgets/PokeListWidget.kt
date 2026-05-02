package com.mn.features.home.presentation.widgets

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mn.features.home.domain.models.PokeDataModel

@Composable
fun PokeListWidget(
    modifier: Modifier,
    pokeList: List<PokeDataModel>
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(20.dp),
    ) {
        items(pokeList.size) { index ->
            val pokeData = pokeList[index]
            pokeData.id = index + 1
            PokeCardWidget(
                pokeData
            )
        }
    }
}