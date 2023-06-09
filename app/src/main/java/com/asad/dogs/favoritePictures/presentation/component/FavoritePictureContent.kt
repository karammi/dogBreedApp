package com.asad.dogs.favoritePictures.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.asad.dogs.favoritePictures.domain.model.FavoritePictureResponse

@Composable
fun FavoritePictureContent(currentBreed: FavoritePictureResponse) {
    Column {
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = currentBreed.title ?: "",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h6,
        )

        Spacer(modifier = Modifier.height(8.dp))

        currentBreed.urls?.let {
            FavoritePictureListContent(
                images = it,
                backgroundColor = MaterialTheme.colors.surface,
            )
        }
    }
}
