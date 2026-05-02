package com.mn.features.home.presentation.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mn.core.designsystem.widgets.UrlImage
import com.mn.features.home.domain.models.PokeDataModel

@Composable
fun PokeCardWidget(
    pokeDataModel: PokeDataModel
) {
    Card(
        modifier = Modifier.padding(10.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            pokeDataModel.url?.let {
                UrlImage(
                    modifier = Modifier.size(100.dp),
                    url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokeDataModel.id}.png"
                )
            }
            Text(pokeDataModel.name)
        }
    }
}


@Preview
@Composable
private fun Preview() {
    PokeCardWidget(
        PokeDataModel(
            name = "Pikachu",
            url = ""
        )
    )
}