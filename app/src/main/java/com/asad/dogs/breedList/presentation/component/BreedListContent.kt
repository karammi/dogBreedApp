package com.asad.dogs.breedList.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.asad.dogs.breedList.domain.model.BreedModel

@Composable
fun BreedListContent(data: List<BreedModel>, onBreedModelItemClicked: (BreedModel) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(count = 2),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 64.dp),
    ) {
        items(
            data.size,
            key = { index -> data[index].title },
            contentType = { BreedModel::class },
        ) { index ->
            BreedModelItem(
                breedModel = data[index],
                onItemClicked = onBreedModelItemClicked,
            )
        }
    }
}
