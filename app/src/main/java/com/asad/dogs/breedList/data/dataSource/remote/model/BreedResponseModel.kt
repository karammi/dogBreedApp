package com.asad.dogs.breedList.data.dataSource.remote.model

import com.asad.dogs.core.data.util.ResponseStatus
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BreedResponseModel(
    val message: Map<String, List<String>>? = null,
    val status: ResponseStatus? = null,
)
