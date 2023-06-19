package com.asad.dogs.breedList.data.repository

import com.asad.dogs.breedList.data.dataSource.remote.BreedListRemoteDataSource
import com.asad.dogs.breedList.data.mapper.ServerResponseToBreedMapper
import com.asad.dogs.breedList.domain.model.BreedModel
import com.asad.dogs.breedList.domain.repository.BreedListRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ViewModelScoped
class BreedListRepositoryImpl @Inject constructor(
    private val remote: BreedListRemoteDataSource,
    private val serverResponseToBreedMapper: ServerResponseToBreedMapper,
) : BreedListRepository {

    /**
     * for caching breed list, and having single source of truth, insert breeds inside database
     *  solutions:
     *  - we could fetch data directly from endpoint and provide it to up layers
     *      -- this approach just need to map server response data
     *  - we could fetch data from endpoint and insert all of them into database and provide breed list
     *
     * */
    override suspend fun fetchBreeds(): Flow<List<BreedModel>> {
        return remote
            .fetchBreedList()
            .map {
                it.value?.let { it1 ->
                    serverResponseToBreedMapper.mapTo(it1)
                        .message?.map { breed ->
                        BreedModel(
                            title = breed.title,
                            subBreeds = breed.subBreeds,
                        )
                    }
                } ?: emptyList()
            }
    }
}
