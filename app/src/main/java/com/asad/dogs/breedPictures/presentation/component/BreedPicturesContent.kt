package com.asad.dogs.breedPictures.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.asad.dogs.core.presentation.conponent.CustomNetworkImage
import com.asad.dogs.favoritePictures.domain.model.FavoritePictureModel

@Composable
fun BreedPicturesContent(
    breedPictureList: List<FavoritePictureModel>,
    onBreedPictureClicked: (String) -> Unit,
) {
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
                key = { index: Int -> breedPictureList[index].breedUrl },
                contentType = { FavoritePictureModel::class },
            ) { index ->
                val value = breedPictureList[index]
                Box {
                    CustomNetworkImage(
                        url = value.breedUrl,
                        onImageClicked = onBreedPictureClicked,
                        contentDescription = "breed_picture_$index",
                    )
                    AnimatedVisibility(
                        visible = value.isFavorite,
                        enter = fadeIn(),
                        exit = fadeOut(),
                        modifier = Modifier
                            .zIndex(1f)
                            .graphicsLayer(),
                    ) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            tint = MaterialTheme.colorScheme.error,
                            contentDescription = "favorite",
                            modifier = Modifier.align(Alignment.BottomEnd),
                        )
                    }
                }
            }
        }
    }
}
