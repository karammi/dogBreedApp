package com.asad.dogs.breedList.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asad.dogs.breedList.domain.model.BreedModel
import com.asad.dogs.breedList.domain.usecase.FetchBreedListUseCase
import com.asad.dogs.core.di.qualifier.IODispatcherQualifier
import com.asad.dogs.core.presentation.UiState
import com.asad.dogs.core.util.firstCap
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "BreedListViewModel"

/**
 * This class contains all of the [BreedListScreen] functionalities
 * */
@HiltViewModel
class BreedListViewModel @Inject constructor(
    private val fetchBreedListUseCase: FetchBreedListUseCase,
    @IODispatcherQualifier private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {

    var uiState = MutableStateFlow(BreedListUiState())
        private set

    init {
        showLoading()
        fetchBreeds()
    }

    private fun showLoading() {
        viewModelScope.launch {
            uiState.emit(value = uiState.value.copy(breedModelResponse = UiState.Loading))
        }
    }

    /**
     * This method requests server or local database to fetch the breeds.
     * */
    fun fetchBreeds() {
        viewModelScope.launch {
            fetchBreedListUseCase
                .invoke()
                .flowOn(ioDispatcher)
                .filterNotNull()
                .stateIn(
                    viewModelScope,
                    started = SharingStarted.Eagerly,
                    initialValue = emptyList(),
                )
                .collectLatest {
                    val formattedBreeds = formatBreedName(it)
                    val newState =
                        uiState.value.copy(breedModelResponse = UiState.Success(data = formattedBreeds))
                    uiState.emit(newState)
                }
        }
    }

    /**
     * This method replace first char of breed title to upper case(capitalize it).
     * */
    fun formatBreedName(list: List<BreedModel>): List<BreedModel> =
        list.map { it.copy(title = it.title.firstCap()) }
}
