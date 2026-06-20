package ke.don.ma3routes.core.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.More
import androidx.compose.material.icons.automirrored.outlined.SendToMobile
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import ke.don.ma3routes.core.ui.components.buttons.ButtonType
import ke.don.ma3routes.core.ui.components.icons.Ma3IconButton
import ke.don.ma3routes.core.ui.components.icons.neutralAlternateIconButtonColors
import ke.don.ma3routes.core.ui.theme.preview.Ma3PreviewLightDark
import ke.don.ma3routes.core.ui.theme.preview.PreviewContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Ma3TopBar(
    title: String,
    modifier: Modifier = Modifier,
    onBack: (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
) {
    TopAppBar(
        title = { Text(text = title) },
        modifier = modifier,
        actions = actions,
        navigationIcon = {
            if (onBack != null) {
                Ma3IconButton(
                    onClick = onBack,
                    type = ButtonType.Neutral,
                    colors = neutralAlternateIconButtonColors()
                ){
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        }
    )
}

@Ma3PreviewLightDark
@Composable
fun Ma3TopBarPreview(){
    PreviewContent{
        Ma3TopBar(
            title = "Settings",
            onBack = {},
            actions = {
                Ma3IconButton(
                    onClick = {},
                    type = ButtonType.Neutral,
                    colors = neutralAlternateIconButtonColors()
                ) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = null
                    )
                }

                Ma3IconButton(
                    onClick = {},
                    type = ButtonType.Neutral,
                    colors = neutralAlternateIconButtonColors(),
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.SendToMobile,
                        contentDescription = null
                    )
                }
            },
        )
    }
}
