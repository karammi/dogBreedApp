package com.asad.dogs.breedList.presentation.viewModel

import com.asad.dogs.breedList.domain.model.DogModel
import com.asad.dogs.core.presentation.UiState

data class BreedListUiState(
    val breedResponse: UiState<DogModel, String> = UiState.Empty,
    val breedList: List<String>? = null,
)
