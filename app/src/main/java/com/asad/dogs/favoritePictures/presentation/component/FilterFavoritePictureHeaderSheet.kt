package com.asad.dogs.favoritePictures.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.asad.dogs.R
import com.asad.dogs.favoritePictures.domain.model.FavoritePictureResponse

@Composable
fun FilterFavoritePictureHeaderSheet(onBreedClicked: (FavoritePictureResponse?) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(16.dp)
            .background(color = MaterialTheme.colors.surface)
            .clickable { onBreedClicked.invoke(null) },
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = stringResource(id = R.string.filter_favorite_breeds),
            style = MaterialTheme.typography.h6,
            modifier = Modifier
                .fillMaxSize(),
        )
    }
}
