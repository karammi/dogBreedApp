package com.asad.dogs.breedPictures.presentation.viewModel

import com.asad.dogs.core.presentation.UiState
import com.asad.dogs.favoritePictures.domain.model.FavoritePictureModel

data class BreedPictureUiState(
    val breedPictures: UiState<List<FavoritePictureModel>, String> = UiState.Loading,
)
