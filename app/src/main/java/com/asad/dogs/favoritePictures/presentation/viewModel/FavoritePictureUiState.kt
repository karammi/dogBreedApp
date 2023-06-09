package com.asad.dogs.favoritePictures.presentation.viewModel

import com.asad.dogs.core.presentation.UiState
import com.asad.dogs.favoritePictures.domain.model.FavoritePictureResponse

data class FavoritePictureUiState(
    val data: UiState<List<FavoritePictureResponse>, String> = UiState.Empty,
    val filterSheetBreedsData: List<FavoritePictureResponse>? = null,
    val isFilterSheetVisible: Boolean = false,
)
