package com.asad.dogs.breedPictures.domain.model

import com.asad.dogs.core.data.util.ResponseStatus

data class BreedPictureResponse(
    val message: List<String>? = null,
    val status: ResponseStatus? = null,
)
