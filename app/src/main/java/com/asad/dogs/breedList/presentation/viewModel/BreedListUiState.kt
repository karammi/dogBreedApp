package com.asad.dogs.breedList.presentation.viewModel

import com.asad.dogs.breedList.domain.model.BreedModel
import com.asad.dogs.core.presentation.UiState

/**
 * This is a data class that wrapped all of the UI states in the [BreedListUiState].
 * All of the fields are immutable so that they wouldn't violate Functional Programming rules.
 * */
data class BreedListUiState(
    val breedModelResponse: UiState<List<BreedModel>?, String> = UiState.Empty,
    val breedList: List<String>? = null,
)
