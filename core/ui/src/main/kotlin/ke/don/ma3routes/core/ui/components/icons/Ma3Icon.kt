package ke.don.ma3routes.core.ui.components.icons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun Ma3Icon(
    icon: ImageVector,
    color: Color = MaterialTheme.colorScheme.surface,
    onColor: Color = MaterialTheme.colorScheme.onSurface,
) {
    Surface(
        color = color,
        contentColor = onColor,
        modifier = Modifier
            .clip(CircleShape),
    ) {
        Icon(
            icon,
            contentDescription = null,
            modifier = Modifier
                .padding(8.dp)
                .size(20.dp),
        )
    }
}
