package com.asad.dogs.favoritePictures.data.dataSource.local.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.asad.dogs.breedPictures.data.dataSource.local.dao.BreedPictureDao
import com.asad.dogs.breedPictures.data.dataSource.local.entity.PictureEntity
import com.asad.dogs.core.di.module.DatabaseModule
import com.asad.dogs.favoritePictures.di.module.FavoritePictureModule
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
        DatabaseModule::class,
        FavoritePictureModule::class,
    ],
)
@RunWith(AndroidJUnit4::class)
@SmallTest
@HiltAndroidTest
class FavoritePicturesDaoTest {

    @get:Rule
    val hiltAndroidRule = HiltAndroidRule(this)

    @Inject
    lateinit var sut: FavoritePicturesDao

    @Inject
    lateinit var dao: BreedPictureDao

    @Before
    fun setup() {
        hiltAndroidRule.inject()
    }

    @Test
    fun fetchFavoritePictures_shouldReturnAllFavoriteBreeds() = runTest {
        val temp = sut.fetchFavoritePictures()
        assertThat(temp).hasSize(0)
    }

    @Test
    fun fetchFavoritePicturesWithEmpty_shouldReturnAllFavoriteBreeds() = runTest {
        val temp = sut.fetchFavoritePictures("akita")
        assertThat(temp).hasSize(0)
    }

    @Test
    fun fetchFavoritePicturesWithData_shouldReturnAllFavoriteBreeds() = runTest {
        dao.addBreedPicture(PictureEntity("akita", "image"))

        val temp = sut.fetchFavoritePictures("akita")
        assertThat(temp).hasSize(1)
    }
}
