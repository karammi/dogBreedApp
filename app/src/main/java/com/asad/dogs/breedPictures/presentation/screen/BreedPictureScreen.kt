package com.asad.dogs.breedPictures.presentation.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.asad.dogs.breedPictures.presentation.viewModel.BreedPictureViewModel
import com.asad.dogs.core.presentation.CustomNetworkImage

@Composable
fun BreedPictureScreen(
    breed: String,
    viewModel: BreedPictureViewModel = hiltViewModel(),
) {
    // This is supposed the be run only once
    LaunchedEffect(key1 = true) {
        viewModel.fetchDogBreedPictures(breedName = breed)
    }

    val state = viewModel.uiState.collectAsState()

    val onImageClicked: (String) -> Unit = { url ->
        viewModel.onBreedPictureClicked(url)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        state.value.breedPictureResponse.data?.message?.let {
            BreedPictureItemContent(
                list = it,
                onItemClicked = onImageClicked,
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BreedPictureItemContent(list: List<String>, onItemClicked: (String) -> Unit) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(3),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        content = {
            items(list.size) { index ->
                CustomNetworkImage(
                    url = list[index],
                    onImageClicked = onItemClicked,
                )
            }
        },
        modifier = Modifier.fillMaxSize(),
    )
}
