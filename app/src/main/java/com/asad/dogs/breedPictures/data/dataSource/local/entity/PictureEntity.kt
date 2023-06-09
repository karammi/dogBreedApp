package com.asad.dogs.breedPictures.data.dataSource.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.asad.dogs.breedList.data.dataSource.local.dao.BreedListConstants
import com.asad.dogs.breedList.data.dataSource.local.entity.BreedEntity
import com.asad.dogs.breedPictures.data.dataSource.local.dao.PictureConstants

@Entity(
    tableName = PictureConstants.FAVORITE_TABLE_NAME,
    primaryKeys = [
        PictureConstants.BREED_NAME_COLUMN,
        PictureConstants.BREED_URL_COLUMN,
    ],
    foreignKeys = [
        ForeignKey(
            entity = BreedEntity::class,
            parentColumns = [BreedListConstants.BREED_TITLE_COLUMN],
            childColumns = [PictureConstants.BREED_NAME_COLUMN],
            onDelete = ForeignKey.CASCADE,
        ),
    ],
)
data class PictureEntity(
    @ColumnInfo(name = PictureConstants.BREED_NAME_COLUMN)
    val breedName: String,
    @ColumnInfo(name = PictureConstants.BREED_URL_COLUMN)
    val breedUrl: String,
)
