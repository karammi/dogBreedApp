package com.asad.dogs.breedPictures.presentation.viewModel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asad.dogs.breedPictures.domain.usecase.AddBreedPictureUseCase
import com.asad.dogs.breedPictures.domain.usecase.FetchBreedPicturesUseCase
import com.asad.dogs.core.data.dataSource.DataResult
import com.asad.dogs.core.di.qualifier.IODispatcherQualifier
import com.asad.dogs.core.presentation.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "BreedPictureViewModel"

@HiltViewModel
class BreedPictureViewModel @Inject constructor(
    private val fetchBreedPicturesUseCase: FetchBreedPicturesUseCase,
    private val addBreedPictureUseCase: AddBreedPictureUseCase,
    private val savedStateHandle: SavedStateHandle,
    @IODispatcherQualifier private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {

    var uiState = MutableStateFlow(BreedPictureUiState())
        private set

    init {
        Log.d(TAG, "init of : BreedPictureViewModel")
        fetchBreedPictures()
    }

    fun fetchBreedPictures() {
        val breedName = savedStateHandle.get<String>("breedName")
        Log.d(TAG, "fetchBreedPictures: $breedName")
        breedName?.let { breed ->
            viewModelScope.launch {
                val response = fetchBreedPicturesUseCase(breed = breed.lowercase())
                Log.d(TAG, "fetchBreedPictures: $response")
                when (response) {
                    is DataResult.Error -> {
                        val newState =
                            uiState.value.copy(breedPictures = UiState.Error(message = response.exception.message))
                        uiState.emit(newState)
                    }

                    is DataResult.Success -> {
                        val newState =
                            uiState.value.copy(
                                breedPictures = UiState.Success(data = response.value),
                            )

                        uiState.emit(newState)
                    }
                }
            }
        }
    }

    fun onBreedPictureClicked(breedName: String, breedPictureUrl: String) {
        viewModelScope.launch(ioDispatcher) {
            addBreedPictureUseCase.invoke(
                breedName = breedName.lowercase(),
                breedUrl = breedPictureUrl,
            )
        }
    }
}
