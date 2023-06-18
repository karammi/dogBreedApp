package com.asad.dogs.breedPictures.domain.usecase

import com.asad.dogs.breedPictures.domain.repository.BreedPictureRepository
import com.asad.dogs.core.data.dataSource.DataResult
import com.asad.dogs.favoritePictures.domain.model.FavoritePictureModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FakeBreedPictureRepository : BreedPictureRepository {

    private val _favoritePicturesFlow: MutableStateFlow<DataResult<List<FavoritePictureModel>>?> =
        MutableStateFlow(null)
    override val favoritePicturesFlow: StateFlow<DataResult<List<FavoritePictureModel>>?>
        get() = _favoritePicturesFlow

    suspend fun emit(value: DataResult<List<FavoritePictureModel>>) =
        _favoritePicturesFlow.emit(value)

    override suspend fun fetchBreedPictures(breedName: String) {
        val favoritePictureModel =
            FavoritePictureModel(breedName = "", breedUrl = "", isFavorite = true)
        val result = DataResult.Success(listOf(favoritePictureModel))
        _favoritePicturesFlow.emit(result)
    }

    override suspend fun toggleBreedPicture(breedName: String, breedUrl: String) {
        val favoritePictureModel =
            FavoritePictureModel(breedName = "", breedUrl = "", isFavorite = true)
        val result = DataResult.Success(listOf(favoritePictureModel))
        _favoritePicturesFlow.emit(result)
    }
}
