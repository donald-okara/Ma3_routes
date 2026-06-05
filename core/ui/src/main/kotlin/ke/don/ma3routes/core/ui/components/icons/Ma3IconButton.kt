package ke.don.ma3routes.core.ui.components.icons

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import ke.don.ma3routes.core.ui.components.buttons.ButtonType

@Composable 
fun Ma3IconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    type: ButtonType = ButtonType.Primary,
    colors: IconButtonColors = iconButtonColorsFor(type),
    interactionSource: MutableInteractionSource? = null,
    shape: Shape = MaterialTheme.shapes.small,
    content: @Composable () -> Unit,
){
    IconButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = colors,
        interactionSource = interactionSource,
        shape = shape,
        content = content
    ) 
}