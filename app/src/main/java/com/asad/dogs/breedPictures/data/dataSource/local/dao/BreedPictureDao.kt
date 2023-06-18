package com.asad.dogs.breedPictures.data.dataSource.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.asad.dogs.breedPictures.data.dataSource.local.entity.PictureEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BreedPictureDao {

    @Insert(entity = PictureEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBreedPicture(entity: PictureEntity)

    @Delete(entity = PictureEntity::class)
    suspend fun deleteBreedPicture(entity: PictureEntity)

    @Query("SELECT * FROM tbl_favorite WHERE breedName = :name and breedUrl = :url")
    suspend fun getCurrentBreedPicture(name: String, url: String): PictureEntity

    @Query("SELECT * FROM tbl_favorite WHERE breedName = :name")
    fun getFavoriteBreedPictures(name: String): Flow<List<PictureEntity>>

    @Transaction
    suspend fun toggleBreedPicture(entity: PictureEntity) {
        val isBreedPictureFavorite = getCurrentBreedPicture(entity.breedName, entity.breedUrl)
        if (isBreedPictureFavorite != null) {
            deleteBreedPicture(entity)
        } else {
            addBreedPicture(entity)
        }
    }
}
