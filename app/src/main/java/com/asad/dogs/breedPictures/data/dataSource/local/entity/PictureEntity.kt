package com.asad.dogs.breedPictures.data.dataSource.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.asad.dogs.breedPictures.data.dataSource.local.dao.PictureConstants

@Entity(
    tableName = PictureConstants.FAVORITE_TABLE_NAME,
    primaryKeys = [
        PictureConstants.BREED_NAME_COLUMN,
        PictureConstants.BREED_URL_COLUMN,
    ],
)
data class PictureEntity(
    @ColumnInfo(name = PictureConstants.BREED_NAME_COLUMN)
    val breedName: String,
    @ColumnInfo(name = PictureConstants.BREED_URL_COLUMN)
    val breedUrl: String,
)
