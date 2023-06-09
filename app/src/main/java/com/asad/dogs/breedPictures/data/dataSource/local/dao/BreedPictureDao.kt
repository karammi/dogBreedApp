package com.asad.dogs.breedPictures.data.dataSource.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.asad.dogs.breedPictures.data.dataSource.local.entity.PictureEntity

@Dao
interface BreedPictureDao {

    @Insert(entity = PictureEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBreedPicture(entity: PictureEntity)

    @Upsert(entity = PictureEntity::class)
    suspend fun upsertBreedPicture(entity: PictureEntity)

    @Delete(entity = PictureEntity::class)
    suspend fun deleteBreedPicture(entity: PictureEntity)

    @Query("SELECT * FROM tbl_favorite WHERE breedName = :name and breedUrl = :url")
    suspend fun getCurrentBreedPicture(name: String, url: String): PictureEntity

    @Transaction
    suspend fun addOrDeleteBreedPicture(entity: PictureEntity) {
        val isBreedPictureFavorite = getCurrentBreedPicture(entity.breedName, entity.breedUrl)
        if (isBreedPictureFavorite != null) {
            deleteBreedPicture(entity)
        } else {
            addBreedPicture(entity)
        }
    }
}
