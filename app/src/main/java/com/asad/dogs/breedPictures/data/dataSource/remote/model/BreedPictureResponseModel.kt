package com.asad.dogs.breedPictures.data.dataSource.remote.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BreedPictureResponseModel(
    val message:List<String>?=null,
    val status:String? = null
)
