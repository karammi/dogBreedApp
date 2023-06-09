package com.asad.dogs.breedList.presentation.screen

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.asad.dogs.R
import com.asad.dogs.breedList.domain.model.BreedModel
import com.asad.dogs.breedList.presentation.component.BreedListContent
import com.asad.dogs.breedList.presentation.viewModel.BreedListViewModel
import com.asad.dogs.core.presentation.UiState
import com.asad.dogs.core.presentation.conponent.CustomAppBar
import com.asad.dogs.core.presentation.conponent.CustomEmptyComponent
import com.asad.dogs.core.presentation.conponent.CustomErrorComponent
import com.asad.dogs.core.presentation.conponent.CustomLoadingComponent
import com.asad.dogs.core.presentation.util.ComposeUtil
import com.asad.dogs.core.util.SystemUiUtil

@Composable
fun BreedListScreen(
    viewModel: BreedListViewModel = hiltViewModel(),
    onBreedItemClicked: (BreedModel) -> Unit,
    onNavigateToFavorite: () -> Unit,
) {
    SystemUiUtil.ConfigStatusBar(color = MaterialTheme.colorScheme.primary)

    val uiState = ComposeUtil.rememberStateWithLifecycle(stateFlow = viewModel.uiState)

    val localFocusManager = LocalFocusManager.current
    val context = LocalContext.current

    val finishActivity: () -> Unit = {
        (context as? Activity)?.finish()
    }

    BackHandler {
        localFocusManager.clearFocus()

        finishActivity()
    }

    val onRetryClick: () -> Unit = { viewModel.fetchBreeds() }

    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        when (uiState.value.breedModelResponse) {
            UiState.Empty -> CustomEmptyComponent()
            is UiState.Error ->
                CustomErrorComponent(
                    errorTitle = uiState.value.breedModelResponse.message
                        ?: "An error has been occurred!",
                    onRetryClicked = onRetryClick,
                )

            UiState.Loading -> CustomLoadingComponent()

            is UiState.Success -> {
                val breeds = uiState.value.breedModelResponse.data ?: emptyList()
                BreedListContent(
                    data = breeds,
                    onBreedModelItemClicked = onBreedItemClicked,
                )
            }
        }

        CustomAppBar(
            title = stringResource(id = R.string.app_name),
            onIconClicked = onNavigateToFavorite,
        )
    }
}
