package com.asad.dogs.breedPictures.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.asad.dogs.core.presentation.conponent.CustomNetworkImage

@Composable
fun BreedPicturesContent(breedPictureList: List<String>, onBreedPictureClicked: (String) -> Unit) {
    Box(
        modifier = Modifier
            .padding(top = 70.dp),
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
        ) {
            items(
                count = breedPictureList.size,
                key = { index: Int -> breedPictureList[index] },
                contentType = { String::class },
            ) { index ->
                CustomNetworkImage(
                    url = breedPictureList[index],
                    onImageClicked = onBreedPictureClicked,
                    contentDescription = "breed_picture_$index",
                )
            }
        }
    }
}
