package com.asad.dogs.favoritePictures.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.asad.dogs.R
import com.asad.dogs.core.presentation.UiState
import com.asad.dogs.core.presentation.conponent.CustomAppBar
import com.asad.dogs.core.presentation.conponent.CustomEmptyComponent
import com.asad.dogs.core.presentation.conponent.CustomErrorComponent
import com.asad.dogs.core.presentation.conponent.CustomLoadingComponent
import com.asad.dogs.core.presentation.util.ComposeUtil
import com.asad.dogs.favoritePictures.presentation.sheet.FavoriteBreedsBottomSheetContent
import com.asad.dogs.favoritePictures.presentation.component.FavoritePictureContent
import com.asad.dogs.favoritePictures.presentation.component.FavoritePictureIconComponent
import com.asad.dogs.favoritePictures.presentation.viewModel.FavoritePictureViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FavoritePictureScreen(
    viewModel: FavoritePictureViewModel = hiltViewModel(),
    onNavigationUp: () -> Unit,
) {
    val uiState = ComposeUtil.rememberStateWithLifecycle(stateFlow = viewModel.uiState)

    val onRetryClicked: () -> Unit = {}
    val onFilterIconClicked: (Boolean) -> Unit = { value ->
        viewModel.setFilterSheetVisibility(value)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        when (uiState.value.data) {
            UiState.Empty -> CustomEmptyComponent()
            is UiState.Error -> CustomErrorComponent(
                errorTitle = stringResource(id = R.string.error_default_message),
                onRetryClicked = onRetryClicked,
            )

            UiState.Loading -> CustomLoadingComponent()
            is UiState.Success -> {
                Box(
                    modifier = Modifier
                        .padding(top = 70.dp),
                ) {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        val favoriteBreeds = uiState.value.data.data
                        favoriteBreeds?.let { data ->
                            items(data.size) { index ->
                                val value = data[index]
                                FavoritePictureContent(currentBreed = value)
                            }
                        }
                    }
                }
            }
        }

        CustomAppBar(
            title = stringResource(id = R.string.favorite_title_screen),
//            onNavigateUp = onNavigationUp,
            onIconClicked = { onFilterIconClicked.invoke(true) },
            trailingContent = { FavoritePictureIconComponent() },
        )

        if (uiState.value.isFilterSheetVisible) {
            uiState.value.filterSheetBreedsData?.let {
                FavoriteBreedsBottomSheetContent(
                    currentState = ModalBottomSheetValue.Expanded,
                    breeds = it,
                    onBreedClicked = { breed ->
                        onFilterIconClicked.invoke(false)
                        viewModel.filterByFavoriteBreed(breed)
                    },
                )
            }
        }
    }
}
