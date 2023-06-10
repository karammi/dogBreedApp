package com.asad.dogs.favoritePictures.presentation.component

import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.List
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FavoritePictureIconComponent() {
    Icon(
        imageVector = Icons.Rounded.List,
        contentDescription = "favorite_icon",
        modifier = Modifier
            .requiredWidth(24.dp)
            .requiredHeight(48.dp),
        tint = MaterialTheme.colorScheme.primary,
    )
}
