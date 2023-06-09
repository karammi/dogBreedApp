package com.asad.dogs.breedPictures.presentation.viewModel

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.asad.core.util.MainDispatcherRule
import com.asad.dogs.breedPictures.domain.model.BreedPictureResponse
import com.asad.dogs.breedPictures.domain.usecase.AddBreedPictureUseCase
import com.asad.dogs.breedPictures.domain.usecase.FetchBreedPicturesUseCase
import com.asad.dogs.core.data.dataSource.DataResult
import com.asad.dogs.core.data.util.ResponseStatus
import com.asad.dogs.core.presentation.UiState
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BreedPictureViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    lateinit var viewModel: BreedPictureViewModel

    val fetchBreedPicturesUseCase = mockk<FetchBreedPicturesUseCase>()
    val addBreedPictureUseCase = mockk<AddBreedPictureUseCase>()
//    val savedStateHandle = mockk<SavedStateHandle>(relaxUnitFun = true, relaxed = true)

    @Before
    fun setup() {
        val savedStateHandle = SavedStateHandle()
        savedStateHandle["breedName"] = "breedName"

        viewModel = BreedPictureViewModel(
            fetchBreedPicturesUseCase,
            addBreedPictureUseCase,
            savedStateHandle,
            StandardTestDispatcher(mainDispatcherRule.testDispatcher.scheduler),
        )
    }

    @Test
    fun `test something simple`() {
        val savedStateHandle = mockk<SavedStateHandle>(relaxUnitFun = true, relaxed = true)
        val breedPictureResponse =
            BreedPictureResponse(message = emptyList(), status = ResponseStatus.success)
        val breedName = "akito"
        coEvery { fetchBreedPicturesUseCase.invoke(breedName) } returns DataResult.Success(
            breedPictureResponse,
        )
        viewModel = BreedPictureViewModel(
            fetchBreedPicturesUseCase,
            addBreedPictureUseCase,
            savedStateHandle,
            StandardTestDispatcher(mainDispatcherRule.testDispatcher.scheduler),
        )

        verify { savedStateHandle["breedName"] = breedName }
    }

    @Test
    fun whenInitialUiState_shouldBeLoadingState() = runTest {
        val expectedUiState = UiState.Loading
        val breedName = "akito"

        val breedPictureResponse =
            BreedPictureResponse(message = emptyList(), status = ResponseStatus.success)
        coEvery { fetchBreedPicturesUseCase.invoke(breedName) } returns DataResult.Success(
            breedPictureResponse,
        )

        viewModel.uiState.test {
            val emission = awaitItem()

            Truth.assertThat(emission.breedPictures).isEqualTo(expectedUiState)

            cancelAndConsumeRemainingEvents()
        }
    }
}
