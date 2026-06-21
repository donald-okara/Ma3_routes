package ke.don.ma3routes.features.profile.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ke.don.ma3routes.core.domain.model.ThemeConfig
import ke.don.ma3routes.core.domain.model.UserDomain
import ke.don.ma3routes.core.resources.R
import ke.don.ma3routes.core.ui.components.Ma3TopBar
import ke.don.ma3routes.core.ui.components.buttons.ButtonType
import ke.don.ma3routes.core.ui.components.buttons.Ma3Button
import ke.don.ma3routes.core.ui.components.buttons.buttonColorsFor
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
                                    Column(
                                        verticalArrangement = Arrangement.spacedBy(8.dp)
                                    ) {
                                        Ma3EnumSwitch(
                                            value = theme,
                                            onValueChange = onThemeChange,
                                            modifier = Modifier.fillMaxWidth()
                                        )

                                        AnimatedVisibility(visible = theme == ThemeConfig.DYNAMIC) {
                                            Text(
                                                text = stringResource(R.string.settings_dynamic_theme_description),
                                                style = MaterialTheme.typography.bodySmall,
                                                color = MaterialTheme.colorScheme.onSurfaceVariant
                                            )
                                        }
                                    }
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
                    type = ButtonType.Outlined,
                    colors = buttonColorsFor(ButtonType.Outlined).copy(
                        contentColor = MaterialTheme.colorScheme.error,
                    ),
                    border = BorderStroke(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.error,
                    ),
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
                    text = "Ma3 routes Version 1.0",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Ma3PreviewLightDark
@Composable
fun SettingsScreenPreview(){
    val isSystemDark = isSystemInDarkTheme()

    var themeConfig by remember {
        mutableStateOf(
            if (isSystemDark)
                ThemeConfig.DARK
            else ThemeConfig.LIGHT
        )
    }
    PreviewContent(
        darkTheme = themeConfig == ThemeConfig.DARK
    ) {
        SettingsScreenComponent(
            user = UserDomain(
                id= "",
                name = "Lisa F. Temecula"
            ),
            theme = themeConfig,
            onThemeChange = {
                themeConfig = it
            }
        )
    }
}
