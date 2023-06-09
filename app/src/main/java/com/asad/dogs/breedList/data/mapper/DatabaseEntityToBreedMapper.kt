package com.asad.dogs.breedList.data.mapper

import com.asad.dogs.breedList.data.dataSource.local.entity.BreedEntity
import com.asad.dogs.breedList.domain.model.BreedModel
import com.asad.dogs.core.data.mapper.CustomMapper
import com.asad.dogs.core.util.createListFromString
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

/**
 * this class is used to map database [BreedEntity] to domain [BreedModel] model
 * */
@ViewModelScoped
class DatabaseEntityToBreedMapper @Inject constructor() :
    CustomMapper<BreedEntity, BreedModel> {
    override fun mapTo(entity: BreedEntity): BreedModel {
        val subBreedList = entity.subBreeds.createListFromString(",")

        return BreedModel(
            title = entity.title,
            subBreeds = subBreedList,
            hasSubBreed = subBreedList.isNotEmpty(),
            isFavorite = entity.isFavorite,
        )
    }
}
