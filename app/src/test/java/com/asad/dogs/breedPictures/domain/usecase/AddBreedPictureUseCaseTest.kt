package com.asad.dogs.breedPictures.domain.usecase

import com.asad.dogs.breedPictures.domain.repository.BreedPictureRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class AddBreedPictureUseCaseTest {

    val repository = mockk<BreedPictureRepository>()
    lateinit var addBreedPictureUseCase: AddBreedPictureUseCase

    @Before
    fun setup() {
        addBreedPictureUseCase = AddBreedPictureUseCase(repository)
    }

    @Test
    fun test() = runTest {
        val breedName = "akita"
        val breedUrl = "fake_url"

        coEvery {
            repository.addBreedPicture(
                breedName = breedName,
                breedUrl = breedUrl,
            )
        } returns Unit

        addBreedPictureUseCase.invoke(breedName = breedName, breedUrl = breedUrl)

        coVerify(exactly = 1) { repository.addBreedPicture(breedUrl = breedUrl, breedName = breedUrl) }
    }
}
