package com.asad.dogs.breedPictures.presentation.viewModel

import com.asad.dogs.breedPictures.data.dataSource.remote.model.BreedPictureResponseModel
import com.asad.dogs.core.presentation.UiState

data class BreedPictureUiState(
    val breedPictureResponse: UiState<BreedPictureResponseModel, String> = UiState.Empty,
)
