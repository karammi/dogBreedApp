package com.asad.dogs.breedList.data.dataSource.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.asad.dogs.breedList.data.dataSource.local.dao.BreedListConstants

@Entity(tableName = BreedListConstants.BREED_TABLE_NAME)
data class BreedEntity(
    @PrimaryKey
    @ColumnInfo(name = BreedListConstants.BREED_TITLE_COLUMN)
    val title: String,
    @ColumnInfo(name = BreedListConstants.SUB_BREEDS_COLUMN) val subBreeds: String,
    @ColumnInfo(name = BreedListConstants.IS_FAVORITE_COLUMN) val isFavorite: Boolean,
)
