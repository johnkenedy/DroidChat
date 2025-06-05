package br.com.ada.droidchat.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.ada.droidchat.ui.theme.DroidChatTheme

const val CONTENT_CHANGE_ANIMATION_DURATION = 2000
const val FADE_OUT_DURATION = 0
const val FADE_IN_DURATION = 1800

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    loadingText: String = "Please wait...",
) {
    val contentAlpha = remember { Animatable(1f) }
    var previousIsLoadingState by remember { mutableStateOf(isLoading) }

    LaunchedEffect(isLoading) {
        if (isLoading != previousIsLoadingState) {
            contentAlpha.animateTo(
                targetValue = 0f,
                animationSpec = tween(durationMillis = FADE_OUT_DURATION)
            )
            contentAlpha.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = FADE_IN_DURATION)
            )

            previousIsLoadingState = isLoading
        } else {
            if (contentAlpha.value != 1f) {
                contentAlpha.snapTo(1f)
            }
        }
    }

    Button(
        onClick = {
            onClick()
        },
        modifier = modifier
            .height(64.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.primary,
            disabledContentColor = MaterialTheme.colorScheme.primary,
            disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer.copy(0.8f),
        ),
        enabled = !isLoading
    ) {
        Box(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = tween(durationMillis = CONTENT_CHANGE_ANIMATION_DURATION)
                ),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .graphicsLayer(alpha = contentAlpha.value)
            ) {
                if (isLoading) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(16.dp),
                            color = Color.White,
                            strokeWidth = 2.dp,
                            strokeCap = StrokeCap.Round,
                        )
                        Spacer(modifier = Modifier.width(8.dp))

                        val textAlpha = remember { Animatable(0f) } // Start transparent

                        LaunchedEffect(Unit) {
                            textAlpha.animateTo(
                                targetValue = 1f,
                                animationSpec = tween(
                                    durationMillis = 300, // Duration of the fade-in
                                    delayMillis = 200    // The delay BEFORE the animation starts
                                )
                            )
                        }

                        Text(
                            text = loadingText,

                            color = MaterialTheme.colorScheme.onPrimary,
                            style = MaterialTheme.typography.labelLarge,
                            modifier = Modifier.graphicsLayer(alpha = textAlpha.value) // Apply animated alpha
                        )
                    }
                } else {
                    Text(
                        text = text,
                        Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.labelLarge,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}


@Preview()
@Composable
private fun PrimaryButtonPreview() {
    DroidChatTheme {
        var isLoading by remember { mutableStateOf(false) }
        PrimaryButton(
            onClick = { isLoading = !isLoading },
            text = "Primary Button",
            loadingText = "Please wait...",
            modifier = Modifier.fillMaxWidth(),
            isLoading = isLoading
        )
    }
}

@Preview()
@Composable
private fun PrimaryButtonLoadingPreview() {
    DroidChatTheme {
        PrimaryButton(
            onClick = { },
            text = "Primary Button",
            loadingText = "Please wait...",
            modifier = Modifier.fillMaxWidth(),
            isLoading = true
        )
    }
}