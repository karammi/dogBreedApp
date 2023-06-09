package com.asad.dogs.breedList.data.mapper

import com.asad.dogs.breedList.data.dataSource.local.entity.BreedEntity
import com.asad.dogs.breedList.domain.model.BreedModel
import com.asad.dogs.core.data.mapper.CustomMapper
import com.asad.dogs.core.util.createListFromString
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

/**
 * This mapper class is responsible for mapping the database result to domain model objects within the app.
 * map database [BreedEntity] to domain [BreedModel] model
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
