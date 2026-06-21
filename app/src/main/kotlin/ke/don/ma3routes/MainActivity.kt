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
package ke.don.ma3routes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import dagger.hilt.android.AndroidEntryPoint
import ke.don.koffee.annotations.ExperimentalKoffeeApi
import ke.don.koffee.ui.KoffeeBar
import ke.don.ma3routes.core.ui.navigation.Ma3Screens
import ke.don.ma3routes.core.ui.navigation.Navigator
import ke.don.ma3routes.core.ui.navigation.rememberNavigationState
import ke.don.ma3routes.core.ui.navigation.toEntries
import ke.don.ma3routes.core.ui.theme.Ma3RoutesTheme
import ke.don.ma3routes.features.authentication.screens.LoginScreen
import ke.don.ma3routes.navigation.NavGraph

@OptIn(ExperimentalKoffeeApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        splashScreen.setKeepOnScreenCondition {
            viewModel.uiState.value is MainUiState.Loading
        }

        enableEdgeToEdge()
        setContent {
            val uiState by viewModel.uiState.collectAsState()

            if (uiState is MainUiState.Success) {
                val successState = uiState as MainUiState.Success
                val isLoggedIn = successState.isLoggedIn

                val navigationState = rememberNavigationState(
                    startRoute = if (isLoggedIn) Ma3Screens.HomeScreen else Ma3Screens.LoginScreen,
                    topLevelRoutes = setOf(
                        Ma3Screens.HomeScreen,
                        Ma3Screens.Routes,
                        Ma3Screens.Stages,
                        Ma3Screens.Settings
                    )
                )

                val navigator = remember(navigationState, isLoggedIn) {
                    Navigator(
                        state = navigationState,
                        onNavigateToRestrictedKey = { Ma3Screens.LoginScreen },
                        isLoggedIn = { isLoggedIn }
                    )
                }

                Ma3RoutesTheme(themeConfig = successState.themeConfig) {
                    KoffeeBar {
                        NavGraph(
                            navigator = navigator,
                            navigationState = navigationState,
                        )
                    }
                }
            }
        }
    }
}
