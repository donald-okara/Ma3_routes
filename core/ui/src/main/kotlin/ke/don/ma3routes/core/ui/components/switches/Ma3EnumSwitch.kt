package ke.don.ma3routes.core.ui.components.switches

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.Spring
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ke.don.ma3routes.core.domain.model.ThemeConfig
import ke.don.ma3routes.core.ui.theme.preview.Ma3PreviewLightDark
import ke.don.ma3routes.core.ui.theme.preview.PreviewContent

@Composable
inline fun <reified T : Enum<T>> Ma3EnumSwitch(
    value: T,
    crossinline onValueChange: (T) -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.shapes.medium,
    trackColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    thumbColor: Color = MaterialTheme.colorScheme.surface,
    thumbOutlineColor: Color = MaterialTheme.colorScheme.outline,
    selectedContentColor: Color = MaterialTheme.colorScheme.primary,
    unselectedContentColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
) {
    val options = remember { enumValues<T>() }
    val selectedIndex = options.indexOf(value)

    // M3 Expressive / Bouncy spring constants
    val thumbOffset by animateFloatAsState(
        targetValue = selectedIndex.toFloat(),
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioLowBouncy,
            stiffness = Spring.StiffnessMediumLow
        ),
        label = "ThumbOffset"
    )

    Box(
        modifier = modifier
            .height(48.dp)
            .background(trackColor, shape)
            .padding(4.dp)
    ) {
        // Background Thumb
        Layout(
            content = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(shape)
                        .background(thumbColor)
                        .border(1.dp, thumbOutlineColor, shape)
                )
            }
        ) { measurables, constraints ->
            val itemWidth = constraints.maxWidth / options.size
            val placeable = measurables.first().measure(
                constraints.copy(minWidth = itemWidth, maxWidth = itemWidth)
            )

            layout(constraints.maxWidth, constraints.maxHeight) {
                placeable.placeRelative(
                    x = (thumbOffset * itemWidth).toInt(),
                    y = 0
                )
            }
        }

        // Labels
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            options.forEach { option ->
                val isSelected = option == value
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clip(shape)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) { onValueChange(option) },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = option.name,
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                        color = if (isSelected) selectedContentColor
                                else unselectedContentColor,
                        maxLines = 1
                    )
                }
            }
        }
    }
}

@Ma3PreviewLightDark
@Composable
fun Ma3EnumSwitchPreview(){
    PreviewContent {
        var theme by remember { mutableStateOf(ThemeConfig.LIGHT) }

        Ma3EnumSwitch(
            value = theme,
            onValueChange = { theme = it },
        )
    }
}
