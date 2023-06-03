package com.asad.dogs.breedList.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun BreedItem(breedTitle: String, onItemClicked: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .clickable { onItemClicked.invoke(breedTitle) }
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .semantics {
                contentDescription = ""
            },
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(
            text = breedTitle,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .semantics {
                    contentDescription = breedTitle
                },
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.secondary
        )
    }
}