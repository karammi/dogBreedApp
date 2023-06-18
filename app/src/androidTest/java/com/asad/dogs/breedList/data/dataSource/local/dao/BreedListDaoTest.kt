package com.asad.dogs.breedList.data.dataSource.local.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import app.cash.turbine.test
import com.asad.dogs.breedList.data.dataSource.local.entity.BreedEntity
import com.asad.dogs.breedList.di.module.BreedListModule
import com.asad.dogs.core.di.module.DatabaseModule
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@UninstallModules(
    value = [
//        NetworkModule::class,
        DatabaseModule::class,
        BreedListModule::class,
    ],
)
@RunWith(AndroidJUnit4::class)
@SmallTest
@HiltAndroidTest
class BreedListDaoTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var breedListDao: BreedListDao

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun addBreedItem_shouldReturn_theBreedItem_inFlow() = runTest {
        val fakeData = BreedEntity("akita", "")

        breedListDao.insertBreed(fakeData)

        breedListDao.getBreeds().test {
            val result = awaitItem()
            assertThat(result).hasSize(1)
            assertThat(result.first()).isEqualTo(fakeData)
            cancel()
        }
    }

    @Test
    fun addBreedList_shouldReturn_theBreedList_inFlow() = runTest {
        val fakeData1 = BreedEntity("akita", "")
        val fakeData2 = BreedEntity("afica", "")
        val fakeData3 = BreedEntity("amircan", "")
        val breedList = listOf(fakeData1, fakeData2, fakeData3)

        breedListDao.insertBreedList(breedList)

        breedListDao.getBreeds().test {
            val result = awaitItem()
            assertThat(result).hasSize(3)
            assertThat(result).isEqualTo(breedList)
            cancel()
        }
    }

    @Test
    fun getBreedList_shouldReturn_theZeroBreed_inFlow() = runTest {
        breedListDao.getBreeds().test {
            val result = awaitItem()
            assertThat(result).hasSize(0)
            cancel()
        }
    }

    @Test
    fun getBreedList_shouldReturn_allBreedList_inFlow() = runTest {
        val fakeData1 = BreedEntity("akita", "")
        val fakeData2 = BreedEntity("afica", "sub-type1")
        val fakeData3 = BreedEntity("amircan", "")
        val breedList = listOf(fakeData1, fakeData2, fakeData3)

        breedListDao.insertBreedList(breedList)

        breedListDao.getBreeds().test {
            val result = awaitItem()
            assertThat(result).hasSize(3)
            assertThat(result).isEqualTo(breedList)
            cancel()
        }
    }
}
