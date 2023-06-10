package com.asad.dogs.breedList.domain.model

/**
 * A domain model class representing a dog breed, including relevant attributes and methods
 * */
data class BreedModel(
    val title: String,
    val subBreeds: List<String> = emptyList(),
    val hasSubBreed: Boolean = false,
)
