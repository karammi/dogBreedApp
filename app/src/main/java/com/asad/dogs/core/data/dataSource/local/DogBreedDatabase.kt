package com.asad.dogs.core.data.dataSource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.asad.dogs.favoritePictures.data.dataSource.local.dao.FavoritePicturesDao
import com.asad.dogs.breedList.data.dataSource.local.dao.BreedListDao
import com.asad.dogs.breedList.data.dataSource.local.entity.BreedEntity
import com.asad.dogs.breedPictures.data.dataSource.local.entity.PictureEntity
import com.asad.dogs.breedPictures.data.dataSource.local.dao.BreedPictureDao

@Database(version = 1, entities = [BreedEntity::class, PictureEntity::class], exportSchema = false)
abstract class DogBreedDatabase : RoomDatabase() {

    abstract fun getBreedListDao(): BreedListDao

    abstract fun getFavoriteDao(): BreedPictureDao

    abstract fun getFavorite(): FavoritePicturesDao
}
