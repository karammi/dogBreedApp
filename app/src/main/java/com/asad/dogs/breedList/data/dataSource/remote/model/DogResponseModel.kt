package com.asad.dogs.breedList.data.dataSource.remote.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DogResponseModel(
    val message: Map<String, List<String>>? = null,
    val status: String? = null,
)
