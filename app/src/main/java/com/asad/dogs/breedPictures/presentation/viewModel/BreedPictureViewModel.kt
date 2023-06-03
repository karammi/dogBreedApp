package com.asad.dogs.breedPictures.presentation.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asad.dogs.breedPictures.data.dataSource.remote.model.BreedPictureResponseModel
import com.asad.dogs.breedPictures.domain.repository.BreedPictureRepository
import com.asad.dogs.core.domain.repository.BreedFavoriteRepository
import com.asad.dogs.core.presentation.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "BreedPicture"

@HiltViewModel
class BreedPictureViewModel @Inject constructor(
    private val breedPictureRepository: BreedPictureRepository,
    private val savedStateHandle: SavedStateHandle,
    private val breedFavoriteRepository: BreedFavoriteRepository,
) : ViewModel() {

    val uiState = MutableStateFlow(BreedPictureUiState())

    fun fetchDogBreedPictures(breedName: String) {
        val dogBreedName = savedStateHandle.get<String>("breed") ?: breedName
        dogBreedName.let { name ->
            viewModelScope.launch {
                val response = breedPictureRepository.fetchBreedPicture(dogBreedName = name)
                val newState =
                    uiState.value.copy(
                        breedPictureResponse = UiState.Success(
                            data = response.getOrNull() ?: BreedPictureResponseModel(),
                        ),
                    )

                uiState.emit(newState)
            }
        }
    }

    fun onBreedPictureClicked(breedPictureUrl: String) {
        viewModelScope.launch {
            breedFavoriteRepository
                .onBreedPictureClicked(breedPictureUrl)
        }
    }
}

