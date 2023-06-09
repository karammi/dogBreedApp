package com.asad.dogs.breedList.data.dataSource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.asad.dogs.breedList.data.dataSource.local.entity.BreedEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BreedListDao {
    @Query("SELECT * FROM ${BreedListConstants.BREED_TABLE_NAME}")
    fun getBreeds(): Flow<List<BreedEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBreed(breed: BreedEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBreedList(breeds: List<BreedEntity>)
}

