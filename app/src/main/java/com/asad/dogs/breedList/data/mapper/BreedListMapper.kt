package com.asad.dogs.breedList.data.mapper

import com.asad.dogs.breedList.domain.model.BreedModel
import com.asad.dogs.core.data.mapper.CustomMapper
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

/**
 * this class is used to map [Map<String, List<String>] server response data
 * to List<Breed> to use in domain and upper layers
 *
 * @params: entity: Map<String, List<String>>
 * @return: List<Breed>
 * */

@ViewModelScoped
class BreedListMapper @Inject constructor() :
    CustomMapper<Map<String, List<String>>, List<BreedModel>> {

    override fun mapTo(entity: Map<String, List<String>>): List<BreedModel> {
        return entity.map { currentMap ->
            BreedModel(
                title = currentMap.key,
                subBreeds = currentMap.value,
                hasSubBreed = currentMap.value.isNotEmpty(),
            )
        }
    }
}
