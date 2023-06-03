package com.asad.dogs.breedFavorites.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.asad.dogs.breedFavorites.presentation.viewModel.BreedFavoritesViewModel
import com.asad.dogs.breedPictures.presentation.screen.BreedPictureItemContent

@Composable
fun BreedFavoritesScreen(
    viewModel: BreedFavoritesViewModel = hiltViewModel(),
) {
    val state = viewModel.uiState.collectAsState()

    val onImageClicked: (String) -> Unit = { url ->
    }

    Box(modifier = Modifier.fillMaxSize()) {
        state.value.data?.let {
            BreedPictureItemContent(
                list = it,
                onItemClicked = onImageClicked,
            )
        }
    }
}
