package ke.don.ma3routes.navigation
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import ke.don.ma3routes.core.ui.components.InDevelopment
import ke.don.ma3routes.core.ui.navigation.Ma3Screens
import ke.don.ma3routes.core.ui.navigation.NavigationState
import ke.don.ma3routes.core.ui.navigation.Navigator
import ke.don.ma3routes.core.ui.navigation.toEntries
import ke.don.ma3routes.features.authentication.screens.LoginScreen

@Composable
fun NavGraph(
    navigator: Navigator,
    navigationState: NavigationState,
    entryProvider: (NavKey) -> NavEntry<NavKey> = rememberEntryProvider(),
) {
    val currentKey = navigationState.backStacks[navigationState.topLevelRoute]?.lastOrNull()
    val topLevelRoutes = listOf(
        Ma3Screens.HomeScreen,
        Ma3Screens.Routes,
        Ma3Screens.Stages,
        Ma3Screens.Settings,
    )
    val showNavigationSuite = currentKey in topLevelRoutes

    if (showNavigationSuite) {
        NavigationSuiteScaffold(
            navigationSuiteItems = {
                item(
                    selected = navigationState.topLevelRoute == Ma3Screens.HomeScreen,
                    onClick = { navigator.navigate(Ma3Screens.HomeScreen) },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") }
                )
                item(
                    selected = navigationState.topLevelRoute == Ma3Screens.Routes,
                    onClick = { navigator.navigate(Ma3Screens.Routes) },
                    icon = { Icon(Icons.Default.Map, contentDescription = "Routes") },
                    label = { Text("Routes") }
                )
                item(
                    selected = navigationState.topLevelRoute == Ma3Screens.Stages,
                    onClick = { navigator.navigate(Ma3Screens.Stages) },
                    icon = { Icon(Icons.Default.DirectionsBus, contentDescription = "Stages") },
                    label = { Text("Stages") }
                )
                item(
                    selected = navigationState.topLevelRoute == Ma3Screens.Settings,
                    onClick = { navigator.navigate(Ma3Screens.Settings) },
                    icon = { Icon(Icons.Default.Settings, contentDescription = "Settings") },
                    label = { Text("Settings") }
                )
            }
        ) {
            NavDisplay(
                entries = navigationState.toEntries(entryProvider)
            ) {
                navigator.goBack()
            }
        }
    } else {
        NavDisplay(
            entries = navigationState.toEntries(entryProvider)
        ) {
            navigator.goBack()
        }
    }
}

@Composable
fun rememberEntryProvider(): (NavKey) -> NavEntry<NavKey> {
    return remember {
        entryProvider {
            entry<Ma3Screens.HomeScreen> {
                InDevelopment("Home")
            }
            entry<Ma3Screens.LoginScreen> {
                LoginScreen()
            }
            entry<Ma3Screens.Routes> {
                InDevelopment("Routes")
            }
            entry<Ma3Screens.Stages> {
                InDevelopment("Stages")
            }
            entry<Ma3Screens.Settings> {
                InDevelopment("Settings")
            }
            entry<Ma3Screens.Route> { key ->
                InDevelopment("Route Details for ${key.id}")
            }
            entry<Ma3Screens.Navigation> { key ->
                InDevelopment("Navigation for ${key.id}")
            }
        }
    }
}
