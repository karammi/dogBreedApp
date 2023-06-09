package com.asad.dogs.breedPictures.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.hilt.navigation.compose.hiltViewModel
import com.asad.dogs.breedPictures.presentation.component.BreedPicturesContent
import com.asad.dogs.breedPictures.presentation.viewModel.BreedPictureViewModel
import com.asad.dogs.core.presentation.UiState
import com.asad.dogs.core.presentation.conponent.CustomAppBar
import com.asad.dogs.core.presentation.conponent.CustomEmptyComponent
import com.asad.dogs.core.presentation.conponent.CustomErrorComponent
import com.asad.dogs.core.presentation.conponent.CustomLoadingComponent

@Composable
fun BreedPictureScreen(
    breed: String,
    viewModel: BreedPictureViewModel = hiltViewModel(),
    onNavigationUp: () -> Unit,
) {
    val state = viewModel.uiState.collectAsState()

    val onBreedPictureClicked: (String) -> Unit = { url ->
        viewModel.onBreedPictureClicked(breedName = breed, breedPictureUrl = url)
    }

    val onRetryClicked: () -> Unit = { viewModel.fetchBreedPictures() }

    Box(modifier = Modifier.fillMaxSize().semantics { contentDescription = "BreedPictureScreen" }) {
        when (state.value.breedPictures) {
            UiState.Empty -> CustomEmptyComponent()
            is UiState.Error -> CustomErrorComponent(
                errorTitle = state.value.breedPictures.message ?: "Ops, error occurred!!",
                onRetryClicked = onRetryClicked,
            )

            UiState.Loading -> CustomLoadingComponent()
            is UiState.Success -> {
                val data = state.value.breedPictures.data?.message ?: emptyList()
                BreedPicturesContent(breedPictureList = data, onBreedPictureClicked = onBreedPictureClicked)
            }
        }

        CustomAppBar(title = breed, onNavigateUp = onNavigationUp)
    }
}
