package com.asad.dogs.breedList.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BreedListContentPreview() {
    BreedListContent(
        data = listOf(
            BreedModel(
                title = "akita",
                subBreeds = emptyList(),
                hasSubBreed = false,
            ),
            BreedModel(
                title = "amircan",
                subBreeds = emptyList(),
                hasSubBreed = false,
            ),
            BreedModel(
                title = "corgi",
                subBreeds = emptyList(),
                hasSubBreed = false,
            ),
            BreedModel(
                title = "chow",
                subBreeds = emptyList(),
                hasSubBreed = false,
            ),
        ),
        onBreedModelItemClicked = {},
    )
}
