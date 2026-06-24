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
package ke.don.ma3routes.core.ui.theme.preview

import android.content.res.Configuration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ke.don.ma3routes.core.domain.model.ThemeConfig
import ke.don.ma3routes.core.ui.navigation.LocalNavigator
import ke.don.ma3routes.core.ui.navigation.Ma3Screens
import ke.don.ma3routes.core.ui.navigation.Navigator
import ke.don.ma3routes.core.ui.navigation.rememberNavigationState
import ke.don.ma3routes.core.ui.theme.Ma3RoutesTheme

@Target(AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.BINARY)
@Preview(
    name = "Light",
    group = "Themes",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,
)
@Preview(
    name = "Dark",
    group = "Themes",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
)
annotation class Ma3PreviewLightDark

@Ma3PreviewLightDark
@Composable
fun PreviewContent(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: (@Composable () -> Unit)? = {
        Text("Empty Preview", modifier = Modifier.fillMaxWidth())
    },
) {
    val themeConfig = if (darkTheme) ThemeConfig.DARK else ThemeConfig.LIGHT
    val navigationState = rememberNavigationState(
        startRoute = Ma3Screens.HomeScreen,
        topLevelRoutes = setOf(
            Ma3Screens.HomeScreen,
            Ma3Screens.Routes,
            Ma3Screens.Stages,
            Ma3Screens.Settings,
        )
    )
    val navigator = remember(navigationState) {
        Navigator(
            state = navigationState,
            onNavigateToRestrictedKey = { Ma3Screens.LoginScreen },
            isLoggedIn = { true },
        )
    }
    Ma3RoutesTheme(themeConfig = themeConfig) {
        CompositionLocalProvider(LocalNavigator provides navigator) {
            Surface {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(8.dp),
                ) {
                    content?.invoke()
                }
            }
        }
    }
}
