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
package ke.don.ma3routes.core.ui.navigation

import androidx.compose.runtime.mutableStateOf
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import org.junit.Assert.assertEquals
import org.junit.Test

class NavigatorTest {

    @Test
    fun `restricted route redirects to login when logged out`() {
        val state = navigationState()
        var restrictedTarget: NavKey? = null
        val navigator = Navigator(
            state = state,
            onNavigateToRestrictedKey = { target ->
                restrictedTarget = target
                Ma3Screens.LoginScreen
            },
            isLoggedIn = { false },
        )

        navigator.navigate(Ma3Screens.Settings)

        assertEquals(Ma3Screens.Settings, restrictedTarget)
        assertEquals(Ma3Screens.HomeScreen, state.topLevelRoute)
        assertEquals(
            listOf(Ma3Screens.HomeScreen, Ma3Screens.LoginScreen),
            state.backStacks.getValue(Ma3Screens.HomeScreen).toList(),
        )
    }

    @Test
    fun `restricted route is added when logged in`() {
        val state = navigationState()
        val navigator = Navigator(
            state = state,
            onNavigateToRestrictedKey = { Ma3Screens.LoginScreen },
            isLoggedIn = { true },
        )

        navigator.navigate(Ma3Screens.Settings)

        assertEquals(
            listOf(Ma3Screens.HomeScreen, Ma3Screens.Settings),
            state.backStacks.getValue(Ma3Screens.HomeScreen).toList(),
        )
    }

    @Test
    fun `top level route navigation switches active stack`() {
        val state = navigationState()
        val navigator = Navigator(
            state = state,
            onNavigateToRestrictedKey = { Ma3Screens.LoginScreen },
            isLoggedIn = { false },
        )

        navigator.navigate(Ma3Screens.Routes)

        assertEquals(Ma3Screens.Routes, state.topLevelRoute)
        assertEquals(listOf(Ma3Screens.HomeScreen), state.backStacks.getValue(Ma3Screens.HomeScreen).toList())
        assertEquals(listOf(Ma3Screens.Routes), state.backStacks.getValue(Ma3Screens.Routes).toList())
    }

    @Test
    fun `goBack removes latest entry from active stack`() {
        val state = navigationState()
        val navigator = Navigator(
            state = state,
            onNavigateToRestrictedKey = { Ma3Screens.LoginScreen },
            isLoggedIn = { true },
        )

        navigator.navigate(Ma3Screens.Settings)
        navigator.goBack()

        assertEquals(listOf(Ma3Screens.HomeScreen), state.backStacks.getValue(Ma3Screens.HomeScreen).toList())
    }

    @Test
    fun `goBack from top level root returns to start route`() {
        val state = navigationState()
        state.topLevelRoute = Ma3Screens.Routes
        val navigator = Navigator(
            state = state,
            onNavigateToRestrictedKey = { Ma3Screens.LoginScreen },
            isLoggedIn = { true },
        )

        navigator.goBack()

        assertEquals(Ma3Screens.HomeScreen, state.topLevelRoute)
    }

    @Test
    fun `stacksInUse contains only start route until another stack is active`() {
        val state = navigationState()

        assertEquals(listOf(Ma3Screens.HomeScreen), state.stacksInUse)

        state.topLevelRoute = Ma3Screens.Routes

        assertEquals(listOf(Ma3Screens.HomeScreen, Ma3Screens.Routes), state.stacksInUse)
    }

    private fun navigationState(
        startRoute: NavKey = Ma3Screens.HomeScreen,
        topLevelRoutes: Set<NavKey> = setOf(Ma3Screens.HomeScreen, Ma3Screens.Routes),
    ): NavigationState = NavigationState(
        startRoute = startRoute,
        topLevelRoute = mutableStateOf(startRoute),
        backStacks = topLevelRoutes.associateWith { route -> NavBackStack(route) },
    )
}
