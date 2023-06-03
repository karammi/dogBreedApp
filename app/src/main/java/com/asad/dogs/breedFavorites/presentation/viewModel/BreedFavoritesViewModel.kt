package com.asad.dogs.breedFavorites.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asad.dogs.core.domain.repository.BreedFavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreedFavoritesViewModel @Inject constructor(
    private val breedFavoriteRepository: BreedFavoriteRepository,
) : ViewModel() {

    val uiState = MutableStateFlow(BreedFavoriteUiState())

    init {
        viewModelScope.launch {
            breedFavoriteRepository
                .breedFavoriteFlow
                .filterNotNull()
                .collectLatest { favoriteList ->

                    val newState = uiState.value.copy(data = favoriteList)

                    uiState.emit(newState)
                }
        }
    }
}

data class BreedFavoriteUiState(
    val data: List<String>? = null,
)
