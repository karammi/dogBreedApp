package com.asad.dogs.breedPictures.data.dataSource.remote.api

import com.asad.dogs.breedPictures.data.dataSource.remote.model.BreedPictureResponseModel
import retrofit2.http.GET
import retrofit2.http.Path

interface BreedPictureApi {

    @GET("breed/{dogBreedName}/images")
    suspend fun fetchBreedPictures(@Path("dogBreedName") dogBreedName: String): BreedPictureResponseModel
}
