package com.asad.dogs.breedPictures.presentation.viewModel

import com.asad.dogs.breedPictures.domain.model.BreedPictureResponse
import com.asad.dogs.core.presentation.UiState

data class BreedPictureUiState(
    val breedPictures: UiState<BreedPictureResponse, String> = UiState.Loading,
)
