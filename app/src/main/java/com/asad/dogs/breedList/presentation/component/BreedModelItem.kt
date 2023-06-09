package com.asad.dogs.breedList.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.asad.dogs.R
import com.asad.dogs.breedList.domain.model.BreedModel

@Composable
fun BreedModelItem(breedModel: BreedModel, onItemClicked: (BreedModel) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .requiredHeight(300.dp)
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .semantics {
                contentDescription = "card_breed_model"
            }
            .clickable(onClick = { onItemClicked(breedModel) }),
        elevation = CardDefaults.cardElevation(defaultElevation = 50.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
        ) {
            Image(
                painter = painterResource(R.drawable.dog),
                contentDescription = "breed_list_image",
            )

            Spacer(modifier = Modifier.height(16.dp))

            Column(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(
                    text = breedModel.title,
                    color = Color.Black,
                    fontWeight = FontWeight.Medium,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.semantics {
                        contentDescription = "breed_model_title"
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "${breedModel.subBreeds.size}",
                    color = Color.Black,
                    style = MaterialTheme.typography.labelMedium,
                )
            }
        }
    }
}

@Preview(showBackground = false, showSystemUi = true)
@Composable
fun BreedItemPreview() {
    BreedModelItem(
        BreedModel(
            title = "Akita",
            subBreeds = emptyList(),
            hasSubBreed = false,
            isFavorite = false,
        ),
        onItemClicked = {},
    )
}
