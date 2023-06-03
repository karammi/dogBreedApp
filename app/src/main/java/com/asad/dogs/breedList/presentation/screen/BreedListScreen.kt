package com.asad.dogs.breedList.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.asad.dogs.breedList.presentation.component.BreedItem
import com.asad.dogs.breedList.presentation.viewModel.BreedListViewModel

@Composable
fun BreedListScreen(
    breedListViewModel: BreedListViewModel = hiltViewModel(),
    onNavigate: (String) -> Unit,
    onNavigateToFavorite: () -> Unit,
) {
    val state = breedListViewModel.uiState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            contentAlignment = Alignment.Center,
        ) {
            Button(onClick = onNavigateToFavorite) {
                Text(text = "navigate To Favorite")
            }
        }
        val breeds = state.value.breedList

        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 120.dp),
            modifier = Modifier.padding(top = 100.dp),
        ) {
            items(breeds?.size ?: 0) { index ->
                BreedItem(
                    breedTitle = breeds?.get(index) ?: "no data",
                    onItemClicked = onNavigate,
                )
            }
        }
    }
}
