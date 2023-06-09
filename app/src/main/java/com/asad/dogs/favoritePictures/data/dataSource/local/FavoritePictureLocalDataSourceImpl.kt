package com.asad.dogs.favoritePictures.data.dataSource.local

import com.asad.dogs.core.di.qualifier.CoreCoroutineScope
import com.asad.dogs.core.di.qualifier.IODispatcherQualifier
import com.asad.dogs.favoritePictures.data.dataSource.local.dao.FavoritePicturesDao
import com.asad.dogs.favoritePictures.data.dataSource.local.entity.FavoritePictureEntity
import com.asad.dogs.favoritePictures.data.dataSource.local.model.FavoritePictureResponseModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class FavoritePictureLocalDataSourceImpl @Inject constructor(
    private val favoriteDao: FavoritePicturesDao,
    @CoreCoroutineScope private val coroutineScope: CoroutineScope,
    @IODispatcherQualifier private val ioDispatcher: CoroutineDispatcher,
) : FavoritePictureLocalDataSource {

    private val _favorite: MutableStateFlow<List<FavoritePictureResponseModel>> =
        MutableStateFlow(emptyList())
    override val favoriteBreedFlow: StateFlow<List<FavoritePictureResponseModel>> get() = _favorite

    init {
        coroutineScope.launch {
            loadFavoritePictures()
        }
    }

    /**
     * change dispatcher to IoDispatcher using withContext
     * that inside that always check for cancellation using ensureActive() function
     * */
    override suspend fun fetchFavoritePicturesByBreedName(breed: String?) =
        withContext(ioDispatcher) {
            val queryResult =
                groupBreedsAndMapList(favoriteDao.fetchFavoritePictures(breed = breed))

            _favorite.emit(queryResult)
        }

    override suspend fun loadFavoritePictures() = withContext(ioDispatcher) {
        val queryResult = groupBreedsAndMapList(favoriteDao.fetchFavoritePictures())

        _favorite.emit(queryResult)
    }

    override suspend fun fetchFavoritePictures(): List<FavoritePictureResponseModel> =
        withContext(ioDispatcher) {
            groupBreedsAndMapList(favoriteDao.fetchFavoritePictures())
        }

    private fun groupBreedsAndMapList(breedList: List<FavoritePictureEntity>): List<FavoritePictureResponseModel> =
        breedList.groupBy { it.breedName }
            .map {
                FavoritePictureResponseModel(it.key, it.value)
            }
}
