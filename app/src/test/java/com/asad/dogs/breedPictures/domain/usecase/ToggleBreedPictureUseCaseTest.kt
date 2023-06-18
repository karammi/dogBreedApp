package com.asad.dogs.breedPictures.domain.usecase

import com.asad.dogs.breedPictures.domain.repository.BreedPictureRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class ToggleBreedPictureUseCaseTest {

    private val repository = mockk<BreedPictureRepository>()
    lateinit var toggleBreedPictureUseCase: ToggleBreedPictureUseCase

    @Before
    fun setup() {
        toggleBreedPictureUseCase = ToggleBreedPictureUseCase(repository)
    }

    @Test
    fun `toggleBreedPicture should call repository's toggleBreedPicture with the correct parameters`() =
        runTest {
            // Arrange
            val breedName = "akita"
            val breedUrl = "fake_url"

            coEvery {
                repository.toggleBreedPicture(breedName, breedUrl)
            } returns Unit

            // Act
            toggleBreedPictureUseCase.invoke(breedName, breedUrl)

            // Assert
            coVerify(exactly = 1) {
                repository.toggleBreedPicture(breedName, breedUrl)
            }
        }
}
