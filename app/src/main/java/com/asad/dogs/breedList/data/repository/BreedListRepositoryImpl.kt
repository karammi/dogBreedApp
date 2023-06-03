package com.asad.dogs.breedList.data.repository

import com.asad.dogs.breedList.data.dataSource.remote.BreedListRemoteDataSource
import com.asad.dogs.breedList.data.dataSource.remote.model.DogResponseModel
import com.asad.dogs.breedList.data.mapper.DogResponseMapper
import com.asad.dogs.breedList.domain.model.DogModel
import com.asad.dogs.breedList.domain.repository.BreedListRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class BreedListRepositoryImpl @Inject constructor(
    private val breedListRemoteDataSource: BreedListRemoteDataSource,
    private val dogResponseMapper: DogResponseMapper,
) : BreedListRepository {
    override suspend fun fetchBreedList(): Result<DogModel> {
        return breedListRemoteDataSource
            .fetchBreedList()
            .map { value: DogResponseModel ->
                dogResponseMapper.mapToModel(value)
            }
    }
}
