package com.asad.dogs.breedList.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asad.dogs.breedList.domain.model.DogModel
import com.asad.dogs.breedList.domain.repository.BreedListRepository
import com.asad.dogs.core.presentation.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "RESULT"

@HiltViewModel
class BreedListViewModel @Inject constructor(
    private val breedListRepository: BreedListRepository,
) : ViewModel() {

    val uiState = MutableStateFlow(BreedListUiState())

    init {
        fetchBreedList()
    }

    private fun fetchBreedList() {
        viewModelScope.launch {
            val response = breedListRepository.fetchBreedList()

            val data = response.getOrDefault(DogModel())

            if (data.status == "success") {
                val breedList = data.message?.keys?.toList()

                val newState = uiState.value.copy(
                    breedResponse = UiState.Success(data = data),
                    breedList = breedList,
                )
                uiState.emit(newState)
            } else {
                //error
            }
        }
    }
}
/*{
    "status": "error",
    "message": "Breed not found (master breed does not exist)",
    "code": 404
}*/
