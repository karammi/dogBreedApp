package com.asad.dogs.breedPictures.domain.usecase

import com.asad.dogs.breedPictures.domain.repository.BreedPictureRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class ToggleBreedPictureUseCaseTest {

    val repository = mockk<BreedPictureRepository>()
    lateinit var toggleBreedPictureUseCase: ToggleBreedPictureUseCase

    @Before
    fun setup() {
        toggleBreedPictureUseCase = ToggleBreedPictureUseCase(repository)
    }

    @Test
    fun test() = runTest {
        val breedName = "akita"
        val breedUrl = "fake_url"

        coEvery {
            repository.toggleBreedPicture(
                breedName = breedName,
                breedUrl = breedUrl,
            )
        } returns Unit

        toggleBreedPictureUseCase.invoke(breedName = breedName, breedUrl = breedUrl)

        coVerify(exactly = 1) { repository.toggleBreedPicture(breedUrl = breedUrl, breedName = breedUrl) }
    }
}
