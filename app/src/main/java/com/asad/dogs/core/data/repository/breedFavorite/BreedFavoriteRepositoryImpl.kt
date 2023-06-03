package com.asad.dogs.core.data.repository.breedFavorite

import com.asad.dogs.core.domain.repository.BreedFavoriteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class BreedFavoriteRepositoryImpl @Inject constructor() : BreedFavoriteRepository {

    private val _breedFavoriteFlow: MutableStateFlow<List<String>?> = MutableStateFlow(null)

    override val breedFavoriteFlow: StateFlow<List<String>?> = _breedFavoriteFlow

    override suspend fun addBreedToFavorite(value: String) {
        val currentList = breedFavoriteFlow.value?.toMutableList() ?: mutableListOf()
        currentList.add(value)
        _breedFavoriteFlow.emit(currentList)
    }

    override suspend fun removeBreedFromFavorite(value: String) {
        val currentList = breedFavoriteFlow.value?.toMutableList()
        if (currentList?.contains(value) == true) {
            currentList.remove(value)
        }
        _breedFavoriteFlow.emit(currentList)
    }

    override suspend fun onBreedPictureClicked(url: String) {
        val currentList = breedFavoriteFlow.value?.toList()
        if (currentList?.contains(url) == true) {
            removeBreedFromFavorite(url)
        } else {
            addBreedToFavorite(url)
        }
    }

    override suspend fun clearFavorite() {
        val currentList = breedFavoriteFlow.value?.toMutableList()

        currentList?.clear()

        _breedFavoriteFlow.emit(currentList)
    }
}
