package ke.don.ma3routes.features.profile.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Assignment
import androidx.compose.material.icons.automirrored.outlined.Help
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material.icons.outlined.Assignment
import androidx.compose.material.icons.outlined.Help
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.OfflinePin
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ke.don.ma3routes.core.domain.model.ThemeConfig
import ke.don.ma3routes.core.domain.model.UserDomain
import ke.don.ma3routes.core.ui.components.Ma3TopBar
import ke.don.ma3routes.core.ui.components.buttons.ButtonType
import ke.don.ma3routes.core.ui.components.buttons.Ma3Button
import ke.don.ma3routes.core.ui.components.switches.Ma3EnumSwitch
import ke.don.ma3routes.core.ui.theme.preview.Ma3PreviewLightDark
import ke.don.ma3routes.core.ui.theme.preview.PreviewContent
import ke.don.ma3routes.features.profile.screens.components.HeadedColumn
import ke.don.ma3routes.features.profile.screens.components.ListItem
import ke.don.ma3routes.features.profile.screens.components.ListSegment
import ke.don.ma3routes.features.profile.screens.components.ProfilePictureSegment

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
) {

}

@Composable
fun SettingsScreenComponent(
    user: UserDomain,
    theme: ThemeConfig,
    onThemeChange: (ThemeConfig) -> Unit,
    modifier: Modifier = Modifier,
) {

    Scaffold(
        modifier = modifier,
        topBar = {
            Ma3TopBar(
                title = "Settings",
                onBack = {},
            )
        },
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                ProfilePictureSegment(
                    user = user
                )

                HeadedColumn(
                    title = "Preferences",
                    modifier = Modifier.fillMaxWidth()
                ) {
                    ListSegment(
                        items = listOf(
                            {
                                ListItem(
                                    icon = Icons.Outlined.Palette,
                                    title = "Theme",
                                ){
                                    Ma3EnumSwitch(
                                        value = theme,
                                        onValueChange = onThemeChange,
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }
                            }
                        )
                    )
                }

                HeadedColumn(
                    title = "Navigation",
                    modifier = Modifier.fillMaxWidth()
                ) {
                    ListSegment(
                        items = listOf(
                            {
                                ListItem(
                                    icon = Icons.Outlined.Notifications,
                                    title = "Notifications",
                                )
                            },
                            {
                                ListItem(
                                    icon = Icons.Outlined.History,
                                    title = "Trip History",
                                )
                            },
                            {
                                ListItem(
                                    icon = Icons.Outlined.OfflinePin,
                                    title = "Offline Maps",
                                )
                            }
                        ),
                    )
                }

                HeadedColumn(
                    title = "Support",
                    modifier = Modifier.fillMaxWidth()
                ) {
                    ListSegment(
                        items = listOf(
                            {
                                ListItem(
                                    icon = Icons.AutoMirrored.Outlined.Help,
                                    title = "Help Center",
                                )
                            },
                            {
                                ListItem(
                                    icon = Icons.AutoMirrored.Outlined.Assignment,
                                    title = "Terms of Service"
                                )
                            }
                        )
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Ma3Button(
                    onClick = {},
                    type = ButtonType.Danger,
                    modifier = Modifier.fillMaxWidth()
                ){
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.Logout,
                        contentDescription = null
                    )

                    Text(
                        text = "LOGOUT"
                    )
                }

                Text(
                    text = "Ma3 routes Version 1.0"
                )
            }
        }
    }
}

@Ma3PreviewLightDark
@Composable
fun SettingsScreenPreview(){
    PreviewContent {
        SettingsScreenComponent(
            user = UserDomain(
                id= "",
                name = "Lisa F. Temecula"
            ),
            theme = ThemeConfig.LIGHT,
            onThemeChange = {}
        )
    }
}
