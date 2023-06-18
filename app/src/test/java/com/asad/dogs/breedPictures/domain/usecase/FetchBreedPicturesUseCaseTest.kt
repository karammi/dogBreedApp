package com.asad.dogs.breedPictures.domain.usecase

import com.asad.dogs.breedPictures.domain.repository.BreedPictureRepository
import com.asad.dogs.core.data.dataSource.DataResult
import com.asad.dogs.favoritePictures.domain.model.FavoritePictureModel
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Test

class FetchBreedPicturesUseCaseTest {

    private lateinit var fetchBreedPicturesUseCase: FetchBreedPicturesUseCase

    @Test
    fun `invoke should call fetchBreedPictures with the correct Response`() = runTest {
        // Arrange
        val repository = FakeBreedPictureRepository()
        fetchBreedPicturesUseCase = FetchBreedPicturesUseCase(repository)

        val expectedDataResult =
            DataResult.Success(
                listOf(
                    FavoritePictureModel("akita", "url1"),
                    FavoritePictureModel("amircan", "url2"),
                ),
            )

        backgroundScope.launch {
            fetchBreedPicturesUseCase.observeDataFlow().collectLatest {
                assertThat(it).isEqualTo(expectedDataResult)
            }
        }

        repository.emit(expectedDataResult)
    }

    @Test
    fun `invoke should call fetchBreedPictures with the correct parameters`() = runTest {
        /** Arrange*/
        val breed = "akita"
        var repository = mockk<BreedPictureRepository>()
        fetchBreedPicturesUseCase = FetchBreedPicturesUseCase(repository)

        coEvery { repository.fetchBreedPictures(breedName = breed) } returns Unit

        /** Act*/
        fetchBreedPicturesUseCase(breed)

        /** Assert*/
        coVerify(exactly = 1) { repository.fetchBreedPictures(breedName = breed) }
    }

    @Test
    fun `observeDataFlow should return the repository's favoritePicturesFlow`() = runTest {
        /** Arrange*/
        val repository = FakeBreedPictureRepository()
        fetchBreedPicturesUseCase = FetchBreedPicturesUseCase(repository)

        val expectedDataResult =
            DataResult.Success(
                listOf(
                    FavoritePictureModel("akita", "url1"),
                    FavoritePictureModel("amircan", "url2"),
                ),
            )

        /** Act */
        val observedResult = mutableListOf<DataResult<List<FavoritePictureModel>>?>()
        backgroundScope.launch {
            fetchBreedPicturesUseCase.observeDataFlow().collect { result ->
                /** Assert */
                assertThat(observedResult).hasSize(1)
                assertThat(observedResult[0]).isEqualTo(expectedDataResult)
            }
        }

        repository.emit(expectedDataResult)
    }
}
