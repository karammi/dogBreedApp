package com.asad.dogs.breedList.presentation.component

import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.asad.dogs.R

@Composable
fun BreedListFavoriteIconComponent() {
    Icon(
        imageVector = Icons.Rounded.Favorite,
        contentDescription = stringResource(id = R.string.favorite_icon_content_desc),
        modifier = Modifier
            .requiredWidth(24.dp)
            .requiredHeight(48.dp),
        tint = MaterialTheme.colorScheme.primary,
    )
}
