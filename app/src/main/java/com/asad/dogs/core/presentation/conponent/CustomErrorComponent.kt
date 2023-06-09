package com.asad.dogs.core.presentation.conponent

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.asad.dogs.R

@Composable
fun CustomErrorComponent(errorTitle: String, onRetryClicked: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 70.dp),
        contentAlignment = Alignment.Center,
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Image(painter = painterResource(id = R.drawable.dog), contentDescription = "dog_error")
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = errorTitle,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onRetryClicked.invoke() },
            )
        }
    }
}
