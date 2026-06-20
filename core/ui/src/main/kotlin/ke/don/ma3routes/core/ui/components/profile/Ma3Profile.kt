package ke.don.ma3routes.core.ui.components.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import ke.don.ma3routes.core.domain.util.getInitials
import ke.don.ma3routes.core.ui.theme.preview.Ma3PreviewLightDark
import ke.don.ma3routes.core.ui.theme.preview.PreviewContent

@Composable
fun Ma3Profile(
    name: String,
    modifier: Modifier = Modifier,
    url: String? = null,
    size: Dp = 40.dp
) {
    val initials = name.getInitials()
    val fontSize = (size.value * 0.4).sp

    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.secondaryContainer),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier.padding(4.dp),
        ){
            if (!url.isNullOrBlank()) {
                AsyncImage(
                    model = url,
                    contentDescription = name,
                    modifier = Modifier.size(size),
                    contentScale = ContentScale.Crop,
                    error = null, // Fallback is handled by the initial background if image fails
                )
            }

            // Show initials if URL is null/blank OR as a layered placeholder
            if (url.isNullOrBlank()) {
                Text(
                    text = initials,
                    fontSize = fontSize,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                )
            }
        }
    }
}

@Ma3PreviewLightDark
@Composable
fun Ma3ProfilePreview(){
    PreviewContent {
        Ma3Profile(
            name = "Lisa F. Temecula"
        )
    }
}
