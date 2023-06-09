package com.asad.dogs.breedList.domain.model

import com.asad.dogs.core.data.util.ResponseStatus

/**
 *  A domain model class representing a dog breed api response, also it status
 * */
data class BreedResponse(
    val message: List<BreedModel>? = null,
    val status: ResponseStatus? = null,
)
