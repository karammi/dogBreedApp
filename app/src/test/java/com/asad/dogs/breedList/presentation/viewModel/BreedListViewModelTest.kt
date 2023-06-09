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
    fun whenInitialUiState_shouldBeLoadingState() = runTest {
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
    fun whenInitialUiState_shouldObserveData() = runTest {
        /**Arrange*/
        val breed = BreedModel(
            title = "Kos khar",
            subBreeds = listOf("akhond", "kiri"),
            hasSubBreed = true,
            isFavorite = false,
        )

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
        assertThat(viewModel.uiState.value.breedModelResponse.data?.size).isEqualTo(2)
        val expectedUiState = UiState.Success(data = viewModel.formatBreedName(data))
        assertThat(viewModel.uiState.value.breedModelResponse).isEqualTo(expectedUiState)
    }

    @Test
    fun whenInitialUiState_shouldFormatBreedNameList() = runTest {
        /**Arrange*/
        val breed = BreedModel(
            title = "kos khar",
            subBreeds = listOf("akhond", "kiri"),
            hasSubBreed = true,
            isFavorite = false,
        )

        val data = listOf(breed, breed)

        coEvery { fetchBreedListUseCase.invoke() } returns flow {
            emit(data)
        }

        viewModel = BreedListViewModel(
            fetchBreedListUseCase,
            StandardTestDispatcher(mainDispatcherRule.testDispatcher.scheduler),
        )
        advanceUntilIdle()
        /** Act*/
        val formattedData = viewModel.formatBreedName(data)

        /** Assert*/
        val expectedUiState = UiState.Success(data = formattedData)
        assertThat(viewModel.uiState.value.breedModelResponse.data?.size).isEqualTo(2)
        assertThat(viewModel.uiState.value.breedModelResponse).isEqualTo(expectedUiState)
    }
}
