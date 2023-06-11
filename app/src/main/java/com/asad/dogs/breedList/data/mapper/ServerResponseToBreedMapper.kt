package com.asad.dogs.breedList.data.mapper

import com.asad.dogs.breedList.data.dataSource.remote.model.BreedResponseModel
import com.asad.dogs.breedList.domain.model.BreedResponse
import com.asad.dogs.core.data.mapper.CustomMapper
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

/**
 * This mapper class is responsible for mapping the server response to domain model objects within the app.
 * map [BreedResponseModel] server response to [BreedResponse] which is used in domain and upper layers
 * it also uses [BreedListMapper] to map breed list data
 *
 * @params: BreedResponseModel
 * @return: BreedModel
 * */

@ViewModelScoped
class ServerResponseToBreedMapper @Inject constructor(
    private val mapper: BreedListMapper,
) : CustomMapper<BreedResponseModel, BreedResponse> {

    override fun mapTo(entity: BreedResponseModel): BreedResponse {
        return BreedResponse(
            message = mapper.mapTo(entity.message ?: emptyMap()),
            status = entity.status,
        )
    }
}
