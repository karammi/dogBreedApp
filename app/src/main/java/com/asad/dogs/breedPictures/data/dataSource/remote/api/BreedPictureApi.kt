package com.asad.dogs.breedPictures.data.dataSource.remote.api

import com.asad.dogs.breedPictures.data.dataSource.remote.model.BreedPictureResponseModel
import retrofit2.http.GET
import retrofit2.http.Path

interface BreedPictureApi {

    @GET("breed/{breedName}/images")
    suspend fun fetchBreedPictures(@Path("breedName") breedName: String): BreedPictureResponseModel
}
