package com.asad.dogs.breedList.domain.model

data class BreedModel(
    val title: String,
    val subBreeds: List<String> = emptyList(),
    val hasSubBreed: Boolean = false,
    val isFavorite: Boolean = false,
)
