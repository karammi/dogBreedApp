package com.asad.dogs.breedList.domain.usecase

import com.asad.dogs.breedList.domain.model.BreedModel
import com.asad.dogs.breedList.domain.repository.BreedListRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class FetchBreedListUseCaseTest {

    private val repository = mockk<BreedListRepository>()

    /** SUT system under test*/
    lateinit var fetchBreedListUseCase: FetchBreedListUseCase

    @Before
    fun setup() {
        fetchBreedListUseCase = FetchBreedListUseCase(repository)
    }

    @Test
    fun whenFetchBreedUseCaseCalled_thenShouldReturnDataFlow() = runTest {
        /**Arrange*/
        val fakeBreedModel = BreedModel(
            title = "akito",
            subBreeds = emptyList(),
            hasSubBreed = false,
        )

        val fakeDataResult = listOf(fakeBreedModel)

        coEvery { repository.fetchBreeds() } returns flow { emit(fakeDataResult) }

        /** Act*/
        val result = fetchBreedListUseCase.invoke()

        /** Assert*/
        assertThat(result.first()).isEqualTo(fakeDataResult)
    }

    @Test
    fun whenFetchBreedUseCaseCalled_thenShouldReturnEmptyList() = runTest {
        val list = emptyList<BreedModel>()

        coEvery { repository.fetchBreeds() } returns flow { emit(list) }

        val result = fetchBreedListUseCase.invoke()

        assertThat(result.first()).isEqualTo(list)
    }
}
