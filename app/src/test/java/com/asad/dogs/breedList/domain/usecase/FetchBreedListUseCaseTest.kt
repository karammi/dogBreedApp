package com.asad.dogs.breedList.domain.usecase

import com.asad.core.util.MainDispatcherRule
import com.asad.dogs.breedList.domain.model.BreedModel
import com.asad.dogs.breedList.domain.repository.BreedListRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FetchBreedListUseCaseTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val repository = mockk<BreedListRepository>()
    lateinit var fetchBreedListUseCase: FetchBreedListUseCase

    @Before
    fun setup() {
        fetchBreedListUseCase = FetchBreedListUseCase(repository)
    }

    @Test
    fun whenFetchBreedUseCaseCalled_thenShouldReturnFlowOfItsData() = runTest {
        val currentBreed = BreedModel(
            title = "akito",
            subBreeds = emptyList(),
            hasSubBreed = false,
            isFavorite = false,
        )

        val list = listOf(currentBreed)

        coEvery { repository.fetchBreeds() } returns flow { emit(list) }

        val result = fetchBreedListUseCase.invoke()

        assertThat(result.first()).isEqualTo(list)
    }

    @Test
    fun whenFetchBreedUseCaseCalled_thenShouldReturnEmptyList() = runTest {
        val list = emptyList<BreedModel>()

        coEvery { repository.fetchBreeds() } returns flow { emit(list) }

        val result = fetchBreedListUseCase.invoke()

        assertThat(result.first()).isEqualTo(list)
    }
}
