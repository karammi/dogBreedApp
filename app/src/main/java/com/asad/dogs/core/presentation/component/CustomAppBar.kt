package com.asad.dogs.core.presentation.component

import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.asad.dogs.R
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun BoxScope.CustomAppBar(
    title: String,
    onNavigateUp: (() -> Unit)? = null,
    isTransparent: Boolean = false,
    onIconClicked: (() -> Unit)? = null,
    trailingContent: (@Composable () -> Unit)? = null,
) {
    /**
     * This will be updated only when [isTransparent] changes
     * with the help of [derivedStateOf] API
     */
    val elevation by remember(isTransparent) {
        derivedStateOf {
            if (isTransparent) {
                0.dp
            } else {
                10.dp
            }
        }
    }

    val primaryColor = MaterialTheme.colorScheme.surface

    /**
     * This will be updated only when [isTransparent] changes
     * with the help of the [derivedStateOf] API
     */
    val backgroundColor by remember(isTransparent) {
        derivedStateOf {
            if (isTransparent) {
                Color.Transparent
            } else {
                primaryColor
            }
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .requiredHeight(64.dp)
            .align(Alignment.TopCenter)
            .zIndex(1f), // zIndex is added so that card's shadow would be drawn on top of the content
        shape = RectangleShape,
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor,
            contentColor = MaterialTheme.colorScheme.onPrimary,
        ),
        elevation = CardDefaults.cardElevation(elevation),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = if (onIconClicked != null) Arrangement.SpaceBetween else Arrangement.Start,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp), // Due to Material Design 3: https://m3.material.io/components/top-app-bar/specs#e3fd3eba-0444-437c-9a82-071ef03d85b1
        ) {
            onNavigateUp?.let {
                CustomTouchableScale(onClick = it) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBack,
                        contentDescription = stringResource(id = R.string.arrow_back_content_desc),
                        modifier = Modifier
                            .requiredWidth(24.dp)
                            .requiredHeight(48.dp),
                        tint = MaterialTheme.colorScheme.primary,
                    )
                }

                Spacer(modifier = Modifier.requiredWidth(24.dp))
            }

            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.semantics {
                    contentDescription = title
                },
            )

            Spacer(modifier = Modifier.weight(1f))

            onIconClicked?.let {
                CustomTouchableScale(onClick = it) {
                    trailingContent?.invoke()
                }
            }
        }
    }
}

private enum class TouchableScaleState {
    Pressed,
    Idle,
}

@Composable
fun CustomTouchableScale(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable () -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()

    val currentState = remember { MutableTransitionState(TouchableScaleState.Idle) }
    val transition = updateTransition(currentState, label = "transition")

    val contentScale by transition.animateFloat(
        label = "opacity",
        transitionSpec = {
            spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow,
            )
        },
    ) { state ->
        when (state) {
            TouchableScaleState.Pressed -> 0.9f
            TouchableScaleState.Idle -> 1f
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .semantics {
                contentDescription = "CustomTouchableScale"
            }
            .graphicsLayer {
                scaleX = contentScale
                scaleY = contentScale
                clip = false
            }
            .pointerInput(enabled) {
                detectTapGestures(
                    onPress = {
                        if (enabled) {
                            coroutineScope.launch {
                                try {
                                    currentState.targetState = TouchableScaleState.Pressed
                                    awaitRelease()
                                    currentState.targetState = TouchableScaleState.Idle
                                    delay(200)
                                    onClick()
                                } catch (e: CancellationException) {
                                    currentState.targetState = TouchableScaleState.Idle
                                }
                            }
                        }
                    },
                )
            },
    ) {
        content()
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun CustomAppBarPreview() {
    Box(modifier = Modifier.fillMaxSize()) {
        CustomAppBar(
            title = "Dog Breed App",
            onNavigateUp = {},
            isTransparent = false,
            onIconClicked = null,
            trailingContent = {
                Icon(
                    imageVector = Icons.Rounded.Favorite,
                    contentDescription = "favorite_icon",
                    modifier = Modifier
                        .requiredWidth(24.dp)
                        .requiredHeight(48.dp),
                    tint = MaterialTheme.colorScheme.primary,
                )
            },
        )
    }
}
