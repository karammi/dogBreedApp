package com.asad.dogs.breedList.data.dataSource.remote.api

import com.asad.dogs.breedList.data.dataSource.remote.model.BreedResponseModel
import retrofit2.http.GET

/**
 * An API client that communicates with the Dog Breeds API to retrieve breed data list.
 * */
interface BreedListApi {

    @GET("breeds/list/all")
    suspend fun fetchBreedList(): BreedResponseModel
}
