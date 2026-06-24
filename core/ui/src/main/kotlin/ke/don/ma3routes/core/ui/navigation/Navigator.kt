package ke.don.ma3routes.core.ui.navigation

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation3.runtime.NavKey

/**
 * Handles navigation events (forward and back) by updating the navigation state.
 *
 * @property state The navigation state that is modified by this class
 * @property onNavigateToRestrictedKey A lambda that is called when the user attempts to navigate
 * to a key that requires login. This should return the key that represents the login screen.
 * @property isLoggedIn A lambda that returns whether the user is logged in.
 */
class Navigator(
    val state: NavigationState,
    private val onNavigateToRestrictedKey: (targetKey: NavKey?) -> NavKey,
    private val isLoggedIn: () -> Boolean,
) {
    fun navigate(route: NavKey) {
        when (route) {
            is ConditionalNavKey if route.requiresLogin && !isLoggedIn() -> {
                val loginKey = onNavigateToRestrictedKey(route)
                state.backStacks[state.topLevelRoute]?.add(loginKey)
            }

            in state.backStacks.keys -> {
                // This is a top level route, just switch to it.
                state.topLevelRoute = route
            }

            else -> {
                state.backStacks[state.topLevelRoute]?.add(route)
            }
        }
    }

    fun goBack() {
        val currentStack = state.backStacks[state.topLevelRoute]
            ?: error("Stack for ${state.topLevelRoute} not found")

        if (currentStack.size > 1) {
            currentStack.removeLastOrNull()
        } else if (state.topLevelRoute != state.startRoute) {
            // If we're at the base of the current route, go back to the start route stack.
            state.topLevelRoute = state.startRoute
        }
    }
}

val LocalNavigator = staticCompositionLocalOf<Navigator> {
    error("No Navigator provided")
}

/**
 * Interface for keys that can have conditional access based on login status.
 */
interface ConditionalNavKey {
    val requiresLogin: Boolean
}
