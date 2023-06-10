package com.asad.dogs.favoritePictures.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.asad.dogs.core.util.firstCap
import com.asad.dogs.favoritePictures.domain.model.FavoritePictureResponse

@Composable
fun FilterBreedItem(
    breed: FavoritePictureResponse,
    onBreedClicked: (FavoritePictureResponse) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(horizontal = 16.dp)
            .clickable { onBreedClicked.invoke(breed) },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = breed.title?.firstCap() ?: "breed",
            style = MaterialTheme.typography.titleLarge,
        )
        Text(
            text = "${breed.urls?.size} favorite breed",
            color = MaterialTheme.colorScheme.primary,
        )
    }
}
