package com.asad.dogs.favoritePictures.presentation.sheet

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.asad.dogs.favoritePictures.domain.model.FavoritePictureResponse
import com.asad.dogs.favoritePictures.presentation.component.FilterBreedItem
import com.asad.dogs.favoritePictures.presentation.component.FilterFavoritePictureHeaderSheet
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun FavoriteBreedsBottomSheetContent(
    currentState: ModalBottomSheetValue,
    breeds: List<FavoritePictureResponse>,
    onBreedClicked: (FavoritePictureResponse?) -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()

    val bottomSheetState = rememberModalBottomSheetState(
        initialValue = currentState,
    )

    val onHideBottomSheet: () -> Unit = {
        coroutineScope.launch {
            bottomSheetState.hide()
        }
    }

    ModalBottomSheetLayout(
        sheetContent = {
            LazyColumn {
                stickyHeader {
                    FilterFavoritePictureHeaderSheet(onBreedClicked)
                }
                item {
                    Divider()
                }

                items(count = breeds.size) { index ->
                    val currentBreed = breeds[index]
                    FilterBreedItem(
                        breed = currentBreed,
                        onBreedClicked = { breed ->
                            onHideBottomSheet.invoke()
                            onBreedClicked.invoke(breed)
                        },
                    )
                }

                item {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp),
                    )
                }
            }
        },
        sheetState = bottomSheetState,
        scrimColor = Color.Transparent,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Transparent)
                .clickable {
                    onHideBottomSheet.invoke()
                },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
        }
    }
}
