package com.asad.dogs.breedList.data.dataSource.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.asad.dogs.breedList.data.dataSource.local.dao.BreedListDao
import com.asad.dogs.core.data.dataSource.local.DogBreedDatabase
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

class BreedListLocalDataSourceImplTest {
    private lateinit var database: DogBreedDatabase
    private lateinit var breedListDao: BreedListDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            DogBreedDatabase::class.java,
        ).allowMainThreadQueries().build()

        breedListDao = database.getBreedListDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun whenInsertBreeds_thenShouldCollectBreeds() = runTest {
        /**Arrange*/
        /**Act*/
        /**Assert*/
    }
}
