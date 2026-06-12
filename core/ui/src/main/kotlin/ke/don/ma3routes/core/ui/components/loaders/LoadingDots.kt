/*
 * Copyright 2025 Donald Isoe
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ke.don.ma3routes.core.ui.components.loaders

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun LoadingDots(
    modifier: Modifier = Modifier,
    dotCount: Int = 3,
    dotSize: Dp = 12.dp,
    dotSpacing: Dp = 8.dp,
    color: Color = Color.White,
    minAlpha: Float = 0.25f,
    maxAlpha: Float = 1f,
    pulseDuration: Int = 500, // one half-cycle in ms
    delayBetweenDots: Int = 150, // stagger delay in ms
) {
    val infinite = rememberInfiniteTransition(label = "LoadingDotsInfiniteTransition")
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(dotSpacing),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        repeat(dotCount) { index ->
            // Each dot has its own animation with a start delay so they wave
            val alpha by infinite.animateFloat(
                initialValue = minAlpha,
                targetValue = maxAlpha,
                animationSpec = infiniteRepeatable(
                    animation = tween(durationMillis = pulseDuration, easing = LinearEasing),
                    repeatMode = RepeatMode.Reverse,
                    initialStartOffset = StartOffset(offsetMillis = index * delayBetweenDots),
                ),
                label = "LoadingDotAlpha_$index",
            )

            Box(
                modifier = Modifier
                    .size(dotSize)
                    .alpha(alpha)
                    .background(color = color, shape = CircleShape),
            )
        }
    }
}

@Composable
fun LoadingDotsInCircle(
    modifier: Modifier = Modifier,
    circleSize: Int = 24,
    containerColor: Color = Color.Black.copy(alpha = 0.6f),
    dotCount: Int = 3,
    dotSize: Dp = (circleSize / 6).dp,
    dotSpacing: Dp = (circleSize / 12).dp,
    dotColor: Color = Color.White,
    minAlpha: Float = 0.25f,
    maxAlpha: Float = 1f,
    pulseDuration: Int = 500,
    delayBetweenDots: Int = 150,
) {
    Box(
        modifier = modifier
            .size(circleSize.dp)
            .background(containerColor, CircleShape), // adjust color if needed
        contentAlignment = Alignment.Center,
    ) {
        LoadingDots(
            modifier = Modifier,
            dotCount = dotCount,
            dotSize = dotSize,
            dotSpacing = dotSpacing,
            color = dotColor,
            minAlpha = minAlpha,
            maxAlpha = maxAlpha,
            pulseDuration = pulseDuration,
            delayBetweenDots = delayBetweenDots,
        )
    }
}
