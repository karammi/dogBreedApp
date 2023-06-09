package com.asad.dogs.breedPictures.presentation.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asad.dogs.breedPictures.domain.usecase.ToggleBreedPictureUseCase
import com.asad.dogs.breedPictures.domain.usecase.FetchBreedPicturesUseCase
import com.asad.dogs.breedPictures.presentation.util.BreedPictureConstants
import com.asad.dogs.core.data.dataSource.DataResult
import com.asad.dogs.core.di.qualifier.IODispatcherQualifier
import com.asad.dogs.core.presentation.UiState
import com.asad.dogs.favoritePictures.domain.model.FavoritePictureModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "BreedPictureViewModel"

@HiltViewModel
class BreedPictureViewModel @Inject constructor(
    private val fetchBreedPicturesUseCase: FetchBreedPicturesUseCase,
    private val toggleBreedPictureUseCase: ToggleBreedPictureUseCase,
    private val savedStateHandle: SavedStateHandle,
    @IODispatcherQualifier private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {

    var uiState = MutableStateFlow(BreedPictureUiState())
        private set

    init {
        observerBreedPicturesFlow()
        fetchBreedPictures()
    }

    /**
     *  --> stateIn operator
     * This operator avoids restarting the upstream flow in certain situations such as configuration changes.
     * This tip is especially helpful when upstream flows are expensive to create and
     * when these operators are used in ViewModels.
     *
     * fetching data from remote api and local database and then combine them is a bit more heavy
     * operation, because of that during config change i want to prevent fetch data again using
     * SharingStarted.WhileSubscribed(5000)
     * Most of the time to keep the upstream flow active for 5 seconds more after the disappearance
     * of the last collector
     * */
    private fun observerBreedPicturesFlow() {
        viewModelScope.launch {
            fetchBreedPicturesUseCase
                .observeDataFlow()
                .catch { exception -> processCaughtException(exception = exception) }
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5000),
                    initialValue = null,
                )
                .filterNotNull()
                .flowOn(ioDispatcher)
                .collectLatest { result ->
                    processBreedPicturesResponse(result)
                }
        }
    }

    fun fetchBreedPictures() {
        val breedName = savedStateHandle.get<String>(BreedPictureConstants.BREED_NAME)
        breedName?.let { breed ->
            viewModelScope.launch {
                fetchBreedPicturesUseCase
                    .invoke(breed.lowercase())
            }
        }
    }

    fun onBreedPictureClicked(breedName: String, breedPictureUrl: String) {
        viewModelScope.launch(ioDispatcher) {
            toggleBreedPictureUseCase.invoke(
                breedName = breedName.lowercase(),
                breedUrl = breedPictureUrl,
            )
        }
    }

    private suspend fun processBreedPicturesResponse(result: DataResult<List<FavoritePictureModel>>) {
        when (result) {
            is DataResult.Error -> {
                val newState =
                    uiState.value.copy(breedPictures = UiState.Error(message = result.exception.message))
                uiState.emit(newState)
            }

            is DataResult.Success -> {
                val newState =
                    uiState.value.copy(
                        breedPictures = UiState.Success(data = result.value),
                    )

                uiState.emit(newState)
            }
        }
    }

    private suspend fun processCaughtException(exception: Throwable) {
        val newState = uiState.value.copy(
            breedPictures = UiState.Error(
                message = exception.message ?: exception.localizedMessage,
            ),
        )
        uiState.emit(newState)
    }
}
