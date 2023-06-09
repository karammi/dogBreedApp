package com.asad.dogs.favoritePictures.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asad.dogs.core.di.qualifier.IODispatcherQualifier
import com.asad.dogs.core.presentation.UiState
import com.asad.dogs.core.util.firstCap
import com.asad.dogs.favoritePictures.domain.model.FavoritePictureResponse
import com.asad.dogs.favoritePictures.domain.usecase.FetchFavoritePicturesUseCase
import com.asad.dogs.favoritePictures.domain.usecase.FetchSelectedBreedsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritePictureViewModel @Inject constructor(
    private val fetchFavoritePicturesUseCase: FetchFavoritePicturesUseCase,
    private val fetchSelectedBreedsUseCase: FetchSelectedBreedsUseCase,
    @IODispatcherQualifier private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {

    var uiState = MutableStateFlow(FavoritePictureUiState())
        private set

    init {
        observeFavoriteBreeds()
        fetchSelectedBreeds()
    }

    /**
     * This method is used to fetch all selected breeds and provide that list for modal bottom sheet
     * */
    private fun fetchSelectedBreeds() {
        viewModelScope.launch {
            val result = fetchSelectedBreedsUseCase.invoke()
            val newState = uiState.value.copy(filterSheetBreedsData = result)
            uiState.emit(newState)
        }
    }

    /**
     * This method is used to collect favorite picture list
     * */
    private fun observeFavoriteBreeds() {
        viewModelScope.launch {
            fetchFavoritePicturesUseCase
                .invoke()
                .flowOn(ioDispatcher)
                .catch { processError(it) }
                .filterNotNull()
                .collectLatest { result -> collectFavoritePictureResponse(result) }
        }
    }

    private suspend fun collectFavoritePictureResponse(data: List<FavoritePictureResponse>) {
        val capitalizedBreedNames = capitalizeBreedName(data)
        val newState = uiState.value.copy(data = UiState.Success(data = capitalizedBreedNames))
        uiState.emit(newState)
    }

    /**
     * This method is used to update uiState with caught error during emission in flow
     * */
    private suspend fun processError(throwable: Throwable) {
        val newState = uiState.value.copy(
            data = UiState.Error(message = throwable.localizedMessage ?: throwable.message),
        )
        uiState.emit(newState)
    }

    /**
     * This method is used to capitalize first char of the breed's name
     * */
    fun setFilterSheetVisibility(value: Boolean) {
        viewModelScope.launch {
            val newState = uiState.value.copy(isFilterSheetVisible = value)
            uiState.emit(newState)
        }
    }

    /**
     * This method is used to filter list of favorite pictures based on breed name
     * */
    fun filterByFavoriteBreed(currentBreed: FavoritePictureResponse?) {
        viewModelScope.launch {
            fetchFavoritePicturesUseCase.fetchFavoriteBreedByName(currentBreed?.title)
        }
    }

    /**
     * This method is used to capitalize first character of breed names
     * */
    private fun capitalizeBreedName(data: List<FavoritePictureResponse>): List<FavoritePictureResponse> {
        return data.map {
            it.copy(title = it.title?.firstCap())
        }
    }
}
