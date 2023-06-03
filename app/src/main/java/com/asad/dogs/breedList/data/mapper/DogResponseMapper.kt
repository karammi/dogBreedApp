package com.asad.dogs.breedList.data.mapper

import com.asad.dogs.breedList.data.dataSource.remote.model.DogResponseModel
import com.asad.dogs.breedList.domain.model.DogModel
import com.asad.dogs.core.data.mapper.ResponseMapper
import javax.inject.Inject

class DogResponseMapper @Inject constructor() : ResponseMapper<DogResponseModel, DogModel> {

    override fun mapToModel(entity: DogResponseModel): DogModel {
        return DogModel(
            message = entity.message,
            status = entity.status,
        )
    }
}

