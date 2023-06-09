package com.asad.dogs.breedPictures.data.repository

import com.asad.dogs.breedPictures.data.dataSource.local.BreedPictureLocalDataSource
import com.asad.dogs.breedPictures.data.dataSource.remote.BreedPictureRemoteDataSource
import com.asad.dogs.breedPictures.data.mapper.ServerResponseToBreedPictureMapper
import com.asad.dogs.breedPictures.domain.model.BreedPictureResponse
import com.asad.dogs.breedPictures.domain.repository.BreedPictureRepository
import com.asad.dogs.core.data.dataSource.DataResult
import com.asad.dogs.core.data.dataSource.map
import com.asad.dogs.core.di.qualifier.IODispatcherQualifier
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class BreedPictureRepositoryImpl @Inject constructor(
    private val remote: BreedPictureRemoteDataSource,
    private val localDatabase: BreedPictureLocalDataSource,
    private val mapper: ServerResponseToBreedPictureMapper,
    @IODispatcherQualifier private val ioDispatcher: CoroutineDispatcher,
) : BreedPictureRepository {

    override suspend fun fetchBreedPictures(breedName: String): DataResult<BreedPictureResponse> =
        withContext(ioDispatcher) {
            remote
                .fetchBreedPictures(breedName = breedName)
                .map {
                    mapper.mapTo(it)
                }
        }

    override suspend fun addBreedPicture(breedName: String, breedUrl: String) {
        withContext(ioDispatcher) {
            localDatabase
                .addBreedPicture(breedName = breedName, breedUrl = breedUrl)
        }
    }
}
