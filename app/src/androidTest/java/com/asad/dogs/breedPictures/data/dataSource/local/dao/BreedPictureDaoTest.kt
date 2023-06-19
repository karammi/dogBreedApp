package com.asad.dogs.breedPictures.data.dataSource.local.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import app.cash.turbine.test
import com.asad.dogs.breedPictures.data.dataSource.local.entity.PictureEntity
import com.asad.dogs.breedPictures.di.module.BreedPictureModule
import com.asad.dogs.core.di.module.DatabaseModule
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@UninstallModules(
    value = [
        DatabaseModule::class,
        BreedPictureModule::class,
    ],
)
@RunWith(AndroidJUnit4::class)
@SmallTest
@HiltAndroidTest
class BreedPictureDaoTest {

    @get:Rule
    val hiltAndroidRule = HiltAndroidRule(this)

    @Inject
    lateinit var sut: BreedPictureDao

    @Before
    fun setup() {
        hiltAndroidRule.inject()
    }

    // addBreedPicture
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun addBreedPicture_shouldBeInsideTheFavoriteBreed() = runTest {
        val entity = PictureEntity(breedName = "akita", "sample url")

        sut.addBreedPicture(entity)

        sut.getFavoriteBreedPictures("akita").test {
            val firstEmit = awaitItem()

            assertThat(firstEmit).hasSize(1)
        }
    }

    // deleteBreedPicture
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun deleteBreedItem_shouldDeleteBreedItem() = runTest(UnconfinedTestDispatcher()) {
        val entity = PictureEntity(breedName = "akita", "sample url")

        sut.addBreedPicture(entity)

        backgroundScope.launch {
            sut.getFavoriteBreedPictures("akita").test {
                val firstEmit = awaitItem()

                assertThat(firstEmit).hasSize(1)

                val secondEmit = awaitItem()

                assertThat(secondEmit).hasSize(1)
            }
        }

        sut.deleteBreedPicture(entity)
    }

    // getCurrentBreedPicture
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getCurrentBreedPicture_shouldReturnSelectedBreedPicture() = runTest {
        val breedName = "akita"
        val breedUrl = "url"
        val entity = PictureEntity(breedName = breedName, breedUrl = breedUrl)

        sut.addBreedPicture(entity)

        val job = launch {
            val response = sut.getCurrentBreedPicture(breedName, breedUrl)
            assertThat(response).isEqualTo(entity)
        }

        job.join()
        job.cancel()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getBreedPictureThatNotExist_shouldReturnEmpty() = runTest {
        val breedName = "akita"
        val breedUrl = "url"
        val entity = PictureEntity(breedName = breedName, breedUrl = breedUrl)

        sut.addBreedPicture(entity)

        val job = launch {
            val response = sut.getCurrentBreedPicture("", "")
            assertThat(response).isEqualTo(null)
        }

        job.join()
        job.cancel()
    }

    // getFavoriteBreedPictures
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getFavoriteBreedPictures_shouldReturnFavoriteBreedPictures() =
        runTest(UnconfinedTestDispatcher()) {
            val breedName = "akita"
            val akitaFavoriteBreed1 = PictureEntity(breedName, "url_1")
            val akitaFavoriteBreed2 = PictureEntity(breedName, "url_2")
            val akitaFavoriteBreed3 = PictureEntity(breedName, "url_3")

            sut.addBreedPicture(akitaFavoriteBreed1)
            sut.addBreedPicture(akitaFavoriteBreed2)
            sut.addBreedPicture(akitaFavoriteBreed3)

            sut.getFavoriteBreedPictures(breedName).test {
                val result = awaitItem()
                assertThat(result).hasSize(3)
                assertThat(result.first()).isEqualTo(akitaFavoriteBreed1)
                assertThat(result.last()).isEqualTo(akitaFavoriteBreed3)
            }
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun toggleBreedPicture_shouldInsertAddFavoriteBreedPicture() = runTest {
        val breedName = "akita"
        val currentPictureEntity = PictureEntity(breedName, breedUrl = "favorite_breed_url")

        sut.toggleBreedPicture(currentPictureEntity)

        sut.getFavoriteBreedPictures(breedName).test {
            val firstEmit = awaitItem()

            assertThat(firstEmit).hasSize(1)
            assertThat(firstEmit.first()).isEqualTo(currentPictureEntity)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun toggleBreedPicture_shouldRemoveFavoriteBreedPicture() = runTest {
        val breedName = "akita"
        val currentPictureEntity = PictureEntity(breedName, breedUrl = "favorite_breed_url")

        sut.toggleBreedPicture(currentPictureEntity)

        sut.getFavoriteBreedPictures(breedName).test {
            val firstEmit = awaitItem()

            assertThat(firstEmit).hasSize(1)
            assertThat(firstEmit.first()).isEqualTo(currentPictureEntity)

            sut.toggleBreedPicture(currentPictureEntity)

            val secondEmit = awaitItem()
            assertThat(secondEmit).hasSize(0)
        }
    }

    // toggleBreedPicture
}
