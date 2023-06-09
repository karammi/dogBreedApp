package com.asad.dogs.breedPictures.data.dataSource.remote.model

import com.asad.dogs.core.data.util.ResponseStatus
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BreedPictureResponseModel(
    val message: List<String>? = null,
    val status: ResponseStatus? = null,
)
