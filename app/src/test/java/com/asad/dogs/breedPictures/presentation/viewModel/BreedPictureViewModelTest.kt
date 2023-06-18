package com.asad.dogs.breedPictures.presentation.viewModel

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.asad.core.util.MainDispatcherRule
import com.asad.dogs.breedPictures.domain.model.BreedPictureResponse
import com.asad.dogs.breedPictures.domain.usecase.FetchBreedPicturesUseCase
import com.asad.dogs.breedPictures.domain.usecase.ToggleBreedPictureUseCase
import com.asad.dogs.core.data.util.ResponseStatus
import com.asad.dogs.core.presentation.UiState
import com.google.common.truth.Truth.assertThat
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

    private val fetchBreedPicturesUseCase = mockk<FetchBreedPicturesUseCase>()
    private val toggleBreedPictureUseCase = mockk<ToggleBreedPictureUseCase>()
//    val savedStateHandle = mockk<SavedStateHandle>(relaxUnitFun = true, relaxed = true)

    @Before
    fun setup() {
        val savedStateHandle = SavedStateHandle()
        savedStateHandle["breedName"] = "breedName"

        viewModel = BreedPictureViewModel(
            fetchBreedPicturesUseCase,
            toggleBreedPictureUseCase,
            savedStateHandle,
            StandardTestDispatcher(mainDispatcherRule.testDispatcher.scheduler),
        )
    }

    @Test
    fun whenInitialViewModel_shouldGetBreedNameFromSaveStateHandler() {
        val savedStateHandle = mockk<SavedStateHandle>(relaxUnitFun = true, relaxed = true)
        val breedPictureResponse =
            BreedPictureResponse(message = emptyList(), status = ResponseStatus.success)
        val breedName = "akito"
        coEvery { fetchBreedPicturesUseCase.invoke(breedName) } returns Unit

        viewModel = BreedPictureViewModel(
            fetchBreedPicturesUseCase,
            toggleBreedPictureUseCase,
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
        coEvery { fetchBreedPicturesUseCase.invoke(breedName) } returns Unit

        viewModel.uiState.test {
            val emission = awaitItem()

            assertThat(emission.breedPictures).isEqualTo(expectedUiState)

            cancelAndConsumeRemainingEvents()
        }
    }
}
