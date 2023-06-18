package com.asad.dogs.breedList.data.dataSource.local

import com.asad.dogs.breedList.data.dataSource.local.dao.ColdFakeBreedListDao
import com.asad.dogs.breedList.data.dataSource.local.entity.BreedEntity
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class BreedListLocalDataSourceImplTest {

    // System under test
    private lateinit var sut: BreedListLocalDataSource
    private lateinit var coldFakeBreedListDao: ColdFakeBreedListDao

    @Before
    fun setup() {
        coldFakeBreedListDao = ColdFakeBreedListDao()
        sut = BreedListLocalDataSourceImpl(coldFakeBreedListDao)
    }

    @Test
    fun whenGetBreedsCalled_ShouldReturnListOfBreeds() = runTest {
        /**Arrange*/
        val breed1 = BreedEntity("akita", "")
        val breed2 = BreedEntity("amircan", "sub1")
        val mockedData = listOf(breed1, breed2)

        /**Act*/
        val first = sut.getBreeds().first()

        /**Assert*/
        assertThat(first).isEqualTo(mockedData)
        assertThat(first).hasSize(2)
        assertThat(first.first()).isEqualTo(breed1)
        assertThat(first.last()).isEqualTo(breed2)
    }

    @Test
    fun whenInsertBreeds_thenShouldCollectNewBreeds() = runTest {
        /**Arrange*/
        val breed1 = BreedEntity("haski", "sub1")
        val mockedData = listOf(breed1)

        /**Act*/
        sut.insertBreedList(mockedData)

        /**Assert*/
        val values = sut.getBreeds().first()
        assertThat(values).hasSize(3)
        assertThat(values[2]).isEqualTo(mockedData.first())
    }
}

