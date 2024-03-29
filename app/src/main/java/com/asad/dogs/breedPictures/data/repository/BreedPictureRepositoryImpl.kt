package com.asad.dogs.breedPictures.data.repository

import com.asad.dogs.breedPictures.data.dataSource.local.BreedPictureLocalDataSource
import com.asad.dogs.breedPictures.data.dataSource.remote.BreedPictureRemoteDataSource
import com.asad.dogs.breedPictures.data.mapper.DatabasePictureEntityToModelMapper
import com.asad.dogs.breedPictures.domain.repository.BreedPictureRepository
import com.asad.dogs.core.data.dataSource.DataResult
import com.asad.dogs.core.di.qualifier.IODispatcherQualifier
import com.asad.dogs.favoritePictures.domain.model.FavoritePictureModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combineTransform
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class BreedPictureRepositoryImpl @Inject constructor(
    private val remote: BreedPictureRemoteDataSource,
    private val localDatabase: BreedPictureLocalDataSource,
    private val pictureEntityMapper: DatabasePictureEntityToModelMapper,
    @IODispatcherQualifier
    private val ioDispatcher: CoroutineDispatcher,
) : BreedPictureRepository {

    private val _favoritePicturesFlow: MutableStateFlow<DataResult<List<FavoritePictureModel>>?> =
        MutableStateFlow(null)
    override val favoritePicturesFlow: StateFlow<DataResult<List<FavoritePictureModel>>?>
        get() = _favoritePicturesFlow

    override suspend fun fetchBreedPictures(breedName: String) {
        val databaseResponse = localDatabase
            .getFavoriteBreedPictures(breedName)
            .map { result -> result.map { pictureEntityMapper.mapTo(it) } }

        val remoteResponse = remote
            .fetchBreedPictures(breedName = breedName)
            .map {
                it.value?.message?.let { list ->
                    serverResponseToFavoriteModel(
                        breedName = breedName,
                        list = list,
                    )
                } ?: emptyList()
            }

        combineTransform(
            remoteResponse,
            databaseResponse,
        ) { remote, local ->

            val result = remote.map {
                if (local.contains(it)) {
                    it.copy(isFavorite = true)
                } else {
                    it
                }
            }
            emit(DataResult.Success(result))
        }
            .flowOn(ioDispatcher)
            .collectLatest {
                _favoritePicturesFlow.emit(it)
            }
    }

    override suspend fun toggleBreedPicture(breedName: String, breedUrl: String) {
        withContext(ioDispatcher) {
            localDatabase
                .toggleBreedPicture(breedName = breedName, breedUrl = breedUrl)
        }
    }

    private fun serverResponseToFavoriteModel(
        breedName: String,
        list: List<String>,
    ): List<FavoritePictureModel> {
        return list.map {
            FavoritePictureModel(breedName = breedName, breedUrl = it, isFavorite = false)
        }
    }
}
