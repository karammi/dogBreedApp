package com.asad.dogs.breedList.domain.model

import com.asad.dogs.core.data.util.ResponseStatus

data class BreedResponse(
    val message: List<BreedModel>? = null,
    val status: ResponseStatus? = null,
)
