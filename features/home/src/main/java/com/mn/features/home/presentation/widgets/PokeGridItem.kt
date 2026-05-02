package com.mn.features.home.presentation.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mn.core.designsystem.widgets.UrlImage
import com.mn.features.domain.models.PokeDataModel

@Composable
fun PokeGridItem(pokeDataModel: PokeDataModel) {
    Card(
        modifier = Modifier
            .size(200.dp)
            .padding(10.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            UrlImage(
                modifier = Modifier.size(100.dp),
                url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokeDataModel.id}.png"
            )
            Text(pokeDataModel.name)
        }
    }
}