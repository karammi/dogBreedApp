package com.asad.dogs.breedList.presentation.viewModel

import app.cash.turbine.test
import com.asad.core.util.MainDispatcherRule
import com.asad.dogs.breedList.domain.model.BreedModel
import com.asad.dogs.breedList.domain.usecase.FetchBreedListUseCase
import com.asad.dogs.core.presentation.UiState
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class BreedListViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: BreedListViewModel

    private val fetchBreedListUseCase = mockk<FetchBreedListUseCase>()

    @Test
    fun whenInitialUiState_shouldEmitLoadingState() = runTest {
        val expectedUiState = UiState.Loading

        coEvery { fetchBreedListUseCase.invoke() } returns flow {
            emit(emptyList())
        }

        viewModel = BreedListViewModel(
            fetchBreedListUseCase,
            StandardTestDispatcher(),
        )

        viewModel.uiState.test {
            val emission = awaitItem()

            assertThat(emission.breedModelResponse).isEqualTo(expectedUiState)

            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun whenBreedListIsReady_shouldUpdatedUiState() = runTest {
        /**Arrange*/
        val breed = BreedModel(
            title = "Akita",
            subBreeds = listOf("test1", "test2"),
            hasSubBreed = true,
        )
        val expectedValue = 2

        val data = listOf(breed, breed)

        coEvery { fetchBreedListUseCase.invoke() } returns flow {
            emit(data)
        }

        /**Act*/
        viewModel = BreedListViewModel(
            fetchBreedListUseCase,
            StandardTestDispatcher(mainDispatcherRule.testDispatcher.scheduler),
        )
        advanceUntilIdle()

        /** Assert*/
        assertThat(viewModel.uiState.value.breedModelResponse.data?.size).isEqualTo(expectedValue)
        val expectedUiState = UiState.Success(data = viewModel.capitalizeBreedName(data))
        assertThat(viewModel.uiState.value.breedModelResponse).isEqualTo(expectedUiState)
    }

    @Test
    fun whenBreedListIsReady_shouldBeCapitalizeBreedListNames() = runTest {
        /**Arrange*/
        val breed = BreedModel(
            title = "Akita",
            subBreeds = listOf("test1", "test2"),
            hasSubBreed = true,
        )

        val data = listOf(breed, breed)
        val expectedValue = 2

        coEvery { fetchBreedListUseCase.invoke() } returns flow { emit(data) }

        viewModel = BreedListViewModel(
            fetchBreedListUseCase,
            StandardTestDispatcher(mainDispatcherRule.testDispatcher.scheduler),
        )

        /**
         * because run test is used StandardTestDispatcher, we should advanceUntilIdle to run
         * coroutines inside queue
         **/
        advanceUntilIdle()

        /** Act*/
        val formattedData = viewModel.capitalizeBreedName(data)

        /** Assert*/
        val expectedUiState = UiState.Success(data = formattedData)
        assertThat(viewModel.uiState.value.breedModelResponse.data?.size).isEqualTo(expectedValue)
        assertThat(viewModel.uiState.value.breedModelResponse).isEqualTo(expectedUiState)
    }
}
