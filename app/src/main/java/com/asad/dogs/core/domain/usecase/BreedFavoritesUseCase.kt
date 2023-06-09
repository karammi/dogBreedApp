package com.asad.dogs.core.domain.usecase

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BreedFavoritesUseCase @Inject constructor() {

    private val _breedFavoriteFlow: MutableStateFlow<List<String>?> = MutableStateFlow(null)

    val breedFavoriteFlow: StateFlow<List<String>?> = _breedFavoriteFlow

    private suspend fun addBreedToFavorite(value: String) {
        val currentList = breedFavoriteFlow.value?.toMutableList() ?: mutableListOf()
        currentList.add(value)
        _breedFavoriteFlow.emit(currentList)
    }

    private suspend fun removeBreedFromFavorite(value: String) {
        val currentList = breedFavoriteFlow.value?.toMutableList()
        if (currentList?.contains(value) == true) {
            currentList.remove(value)
        }
        _breedFavoriteFlow.emit(currentList)
    }

    suspend fun onBreedPictureClicked(url: String) {
        val currentList = breedFavoriteFlow.value?.toList()
        if (currentList?.contains(url) == true) {
            removeBreedFromFavorite(url)
        } else {
            addBreedToFavorite(url)
        }
    }

    suspend fun clearFavorite() {
        val currentList = breedFavoriteFlow.value?.toMutableList()

        currentList?.clear()

        _breedFavoriteFlow.emit(currentList)
    }
}
