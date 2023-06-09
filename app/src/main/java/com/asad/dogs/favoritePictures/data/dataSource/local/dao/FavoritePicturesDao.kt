package com.asad.dogs.favoritePictures.data.dataSource.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.asad.dogs.favoritePictures.data.dataSource.local.entity.FavoritePictureEntity

@Dao
interface FavoritePicturesDao {

    @Query("SELECT * FROM tbl_favorite where (:breed IS NULL OR breedName = :breed)")
    fun fetchFavoritePictures(breed: String? = null): List<FavoritePictureEntity>
}
