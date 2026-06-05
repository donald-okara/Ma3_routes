package ke.don.ma3routes.core.ui.components.buttons

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import ke.don.ma3routes.core.ui.components.loaders.LoadingDots

/**
 * A custom button component for the Ma3routes application.
 *
 * @param onClick The callback to be invoked when the button is clicked.
 * @param text The text to be displayed on the button.
 * @param modifier The modifier to be applied to the button.
 * @param enabled Whether the button is enabled.
 * @param loading Whether the button is in a loading state.
 */
@Composable
fun Ma3Button(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    loading: Boolean = false,
) {
    Ma3Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        loading = loading,
    ) {
        Text(
            text = text
        )
    }
}

/**
 * A custom button component for the Ma3routes application with custom content.
 *
 * @param onClick The callback to be invoked when the button is clicked.
 * @param modifier The modifier to be applied to the button.
 * @param enabled Whether the button is enabled.
 * @param loading Whether the button is in a loading state.
 * @param shape The shape of the button.
 * @param colors The colors to be used for the button.
 * @param elevation The elevation of the button.
 * @param border The border of the button.
 * @param contentPadding The padding to be applied to the button content.
 * @param interactionSource The interaction source for the button.
 * @param content The content of the button.
 */
@Composable
fun Ma3Button(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    loading: Boolean = false,
    shape: Shape = MaterialTheme.shapes.extraSmall,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
    border: BorderStroke? = null,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    interactionSource: MutableInteractionSource? = null,
    content: @Composable RowScope.() -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled && !loading, // Disable button while loading
        shape = shape,
        colors = colors,
        elevation = elevation,
        border = border,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
    ) {
        ButtonContentSwitcher(
            loading = loading
        ) {
            content()
        }
    }
}

/**
 * A switcher that ensures the button size remains consistent by measuring the content.
 * It shows a loading indicator in the center while the original content is hidden but still occupies space.
 */
@Composable
fun ButtonContentSwitcher(
    loading: Boolean,
    content: @Composable RowScope.() -> Unit,
) {
    val density = LocalDensity.current
    var contentSize by remember { mutableStateOf(IntSize.Zero) }

    Box(
        contentAlignment = Alignment.Center,
        propagateMinConstraints = true
    ) {
        // Content is always present but hidden when loading to maintain size
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .onGloballyPositioned { coords ->
                    // Only update size if not loading or if size is zero
                    if (!loading || contentSize == IntSize.Zero) {
                        contentSize = coords.size
                    }
                }
                .alpha(if (loading) 0f else 1f)
        ) {
            content()
        }

        // Loading dots shown in the center
        if (loading) {
            val dotSize = if (contentSize != IntSize.Zero) {
                with(density) { (contentSize.height * 0.4f).toDp() }
            } else {
                12.dp
            }

            LoadingDots(
                color = LocalContentColor.current,
                dotSize = dotSize,
            )
        }
    }
}

@PreviewLightDark
@Composable
fun Ma3ButtonPreview() {
    PreviewContent {
        var loading by remember {
            mutableStateOf(false)
        }

        Ma3Button(
            onClick = {
                loading = !loading
            },
            loading = loading,
            text = "Click me"
        )
    }
}
