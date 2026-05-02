package com.mn.features.home.presentation.widgets

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.mn.features.domain.models.PokeDataModel

@Composable
fun PokeGridWidget(
    pokeList: LazyPagingItems<PokeDataModel>
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(20.dp)
    ) {
        items(pokeList.itemCount) { index ->
            val pokeData = pokeList[index]
            pokeData?.let {
                pokeData.id = index
                PokeGridItem(it)
            }
        }
    }
}