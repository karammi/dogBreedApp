package com.asad.dogs.breedList.data.repository

import com.asad.dogs.breedList.data.dataSource.local.BreedListLocalDataSource
import com.asad.dogs.breedList.data.dataSource.local.entity.BreedEntity
import com.asad.dogs.breedList.data.dataSource.remote.BreedListRemoteDataSource
import com.asad.dogs.breedList.data.dataSource.remote.model.BreedResponseModel
import com.asad.dogs.breedList.data.mapper.DatabaseEntityToBreedMapper
import com.asad.dogs.breedList.data.mapper.ServerResponseToBreedMapper
import com.asad.dogs.breedList.domain.model.BreedModel
import com.asad.dogs.breedList.domain.repository.BreedListRepository
import com.asad.dogs.core.data.dataSource.DataResult
import com.asad.dogs.core.di.qualifier.IODispatcherQualifier
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class BreedListRepositoryImpl @Inject constructor(
    private val remote: BreedListRemoteDataSource,
    private val localDatabase: BreedListLocalDataSource,
    private val serverResponseToBreedMapper: ServerResponseToBreedMapper,
    private val databaseEntityToBreedMapper: DatabaseEntityToBreedMapper,
    @IODispatcherQualifier private val ioDispatcher: CoroutineDispatcher,
) : BreedListRepository {
    override suspend fun fetchBreeds(): Flow<List<BreedModel>> {
        return localDatabase
            .getBreeds()
            .flowOn(ioDispatcher)
            .filterNotNull()
            .map {
                if (it.isEmpty()) {
                    val remoteResponse = remote.fetchBreedList()
                    checkResponseAndInsertToDatabase(remoteResponse = remoteResponse)
                }

                it.map { entity -> databaseEntityToBreedMapper.mapTo(entity) }
            }
            .flowOn(ioDispatcher)
            .catch {
                emitAll(flowOf(emptyList()))
            }
    }

    private fun convertSubBreedsToString(subBreeds: List<String>): String =
        subBreeds.joinToString(",")

    private suspend fun checkResponseAndInsertToDatabase(remoteResponse: DataResult<BreedResponseModel>) =
        withContext(ioDispatcher) {
            if (remoteResponse is DataResult.Success) {
                val breedEntities = mapBreedToBreedEntity(remoteResponse.value)
                insertBreedsToDatabase(breedEntities)
            }
        }

    /**
     * Map breed to database breed entity
     **/
    private suspend fun mapBreedToBreedEntity(breedResponseModel: BreedResponseModel): List<BreedEntity> =
        withContext(ioDispatcher) {
            val mappedBreedResponseToBreedModel =
                serverResponseToBreedMapper.mapTo(breedResponseModel)

            mappedBreedResponseToBreedModel
                .message
                ?.map { breed ->
                    BreedEntity(
                        title = breed.title,
                        subBreeds = convertSubBreedsToString(breed.subBreeds),
                        isFavorite = breed.isFavorite,
                    )
                } ?: emptyList()
        }

    private suspend fun insertBreedsToDatabase(breeds: List<BreedEntity>) {
        withContext(ioDispatcher) {
            localDatabase.insertBreedList(breeds = breeds)
        }
    }
}
