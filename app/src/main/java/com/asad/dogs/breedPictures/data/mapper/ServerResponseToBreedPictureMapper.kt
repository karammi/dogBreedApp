package com.asad.dogs.breedPictures.data.mapper

import com.asad.dogs.breedPictures.data.dataSource.remote.model.BreedPictureResponseModel
import com.asad.dogs.breedPictures.domain.model.BreedPictureResponse
import com.asad.dogs.core.data.mapper.CustomMapper
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class ServerResponseToBreedPictureMapper @Inject constructor() :
    CustomMapper<BreedPictureResponseModel, BreedPictureResponse> {
    override fun mapTo(entity: BreedPictureResponseModel): BreedPictureResponse {
        return BreedPictureResponse(
            message = entity.message,
            status = entity.status,
        )
    }
}
