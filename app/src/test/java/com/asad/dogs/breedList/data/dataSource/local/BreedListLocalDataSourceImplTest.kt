package com.asad.dogs.breedList.data.dataSource.local

import com.asad.dogs.breedList.data.dataSource.local.dao.BreedListDao
import com.asad.dogs.breedList.data.dataSource.local.entity.BreedEntity
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

class BreedListLocalDataSourceImplTest {

    // System under test
    private lateinit var sut: BreedListLocalDataSource
    private lateinit var fakeBreedListDao: FakeBreedListDao

    @Before
    fun setup() {
        fakeBreedListDao = FakeBreedListDao()
        sut = BreedListLocalDataSourceImpl(fakeBreedListDao)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getBreedsShouldReturnListOfBreedEntities() = runTest {
        /**Arrange*/

        val mockedData = listOf(BreedEntity("akita", ""))

//        coEvery { breedListDao.getBreeds() } returns flow { emit(mockedData) }
        /**Act*/
        val result = sut.getBreeds()

        /**Assert*/
        assertThat(result.first()).isEqualTo(mockedData)
    }

    @Test
    fun whenInsertBreeds_thenShouldCollectBreeds() = runTest {
        /**Arrange*/
        /**Act*/
        /**Assert*/
    }
}

class FakeBreedListDao : BreedListDao {

    val breedList = mutableListOf<BreedEntity>()

    override fun getBreeds(): Flow<List<BreedEntity>> {
        return flow { emit(breedList) }
    }

    override suspend fun insertBreed(breed: BreedEntity) {
        breedList.add(breed)
    }

    override suspend fun insertBreedList(breeds: List<BreedEntity>) {
        breedList.addAll(breeds)
    }
}
