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
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import dagger.hilt.android.AndroidEntryPoint
import ke.don.koffee.annotations.ExperimentalKoffeeApi
import ke.don.koffee.ui.KoffeeBar
import ke.don.ma3routes.core.domain.session.SessionManager
import ke.don.ma3routes.core.ui.navigation.Ma3Screens
import ke.don.ma3routes.core.ui.navigation.Navigator
import ke.don.ma3routes.core.ui.navigation.rememberNavigationState
import ke.don.ma3routes.core.ui.navigation.toEntries
import ke.don.ma3routes.core.ui.theme.Ma3RoutesTheme
import ke.don.ma3routes.features.authentication.screens.LoginScreen
import javax.inject.Inject

@OptIn(ExperimentalKoffeeApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val accessToken by sessionManager.getAccessToken().collectAsState(initial = null)
            val isLoggedIn = accessToken != null

            val navigationState = rememberNavigationState(
                startRoute = Ma3Screens.HomeScreen,
                topLevelRoutes = setOf(Ma3Screens.HomeScreen, Ma3Screens.Routes)
            )

            val navigator = remember(navigationState, isLoggedIn) {
                Navigator(
                    state = navigationState,
                    onNavigateToRestrictedKey = { Ma3Screens.LoginScreen },
                    isLoggedIn = { isLoggedIn }
                )
            }

            val entryProvider = remember {
                entryProvider<NavKey> {
                    entry<Ma3Screens.HomeScreen> {
                        Text("Home Screen")
                    }
                    entry<Ma3Screens.LoginScreen> {
                        LoginScreen()
                    }
                    entry<Ma3Screens.Settings> {
                        Text("Profile Screen")
                    }
                }
            }

            Ma3RoutesTheme {
                KoffeeBar {
                    NavDisplay(
                        entries = navigationState.toEntries(entryProvider),
                        onBack = { navigator.goBack() }
                    )
                }
            }
        }
    }
}
