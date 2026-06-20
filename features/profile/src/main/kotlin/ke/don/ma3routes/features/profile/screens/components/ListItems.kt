package ke.don.ma3routes.features.profile.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddAlert
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ke.don.ma3routes.core.domain.model.UserDomain
import ke.don.ma3routes.core.ui.components.card.CardType
import ke.don.ma3routes.core.ui.components.card.Ma3Card
import ke.don.ma3routes.core.ui.components.profile.Ma3Profile
import ke.don.ma3routes.core.ui.theme.preview.Ma3PreviewLightDark
import ke.don.ma3routes.core.ui.theme.preview.PreviewContent

@Composable
fun ListSegment(
    modifier: Modifier = Modifier,
    items: List<@Composable () -> Unit>
) {
    Ma3Card(
        type = CardType.Outlined,
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items.forEachIndexed { index, item ->
                item()
                if (index < items.lastIndex) {
                    HorizontalDivider(
                        thickness = 1.dp,
                        color = MaterialTheme.colorScheme.outlineVariant
                    )
                }
            }
        }
    }
}

@Composable
internal fun HeadedColumn(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
){
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
        modifier = modifier
    ){
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        content()
    }
}

@Composable
internal fun ListItem(
    icon: ImageVector,
    title: String,
    modifier: Modifier = Modifier,
    bottomComponent: (@Composable () -> Unit)? = null
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null
            )

            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
            )
        }

        bottomComponent?.invoke()
    }
}

@Composable
internal fun ProfilePictureSegment(
    modifier: Modifier = Modifier,
    user: UserDomain
) {
    Ma3Card(
        type = CardType.Outlined,
        modifier = modifier.fillMaxWidth(),
    ){
        Row(
            modifier = modifier
                .padding(24.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Ma3Profile(
                name = user.name ?: "Unknown",
                url = user.avatarUrl,
                size = 60.dp,
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = user.name ?: "Unknown",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                )

                Text(
                    text = user.email ?: "Unknown",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}

@Ma3PreviewLightDark
@Composable
fun ListSegmentPreview(){
    PreviewContent {
        ListSegment(
            items = listOf(
                { PlaceholderComponent() },
                { PlaceholderComponent() },
                { PlaceholderComponent() },
            )
        )
    }
}

@Ma3PreviewLightDark
@Composable
fun ProfilePictureSegmentPreview(){
    PreviewContent {
        ProfilePictureSegment(
            user = UserDomain(
                id= "",
                name = "Lisa F. Temecula"
            )
        )
    }
}

@Ma3PreviewLightDark
@Composable
fun ListItemPreview(){
    PreviewContent {
        ListItem(
            icon = Icons.Outlined.AddAlert,
            title = "Notifications"
        ){
            PlaceholderComponent()
        }
    }
}

@Ma3PreviewLightDark
@Composable
fun HeadedColumnPreview(){
    PreviewContent {
        HeadedColumn(
            title = "Preview"
        ){
            PlaceholderComponent()
        }
    }
}

@Composable
private fun PlaceholderComponent() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colorScheme.primaryContainer)
            .fillMaxWidth(),
    ) {
        Text(
            text = "Preview Content",
            modifier = Modifier.padding(16.dp),
            color = MaterialTheme.colorScheme.onPrimaryContainer,
        )
    }
}
