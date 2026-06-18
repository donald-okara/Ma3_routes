package ke.don.ma3routes.core.ui.navigation

import androidx.navigation3.runtime.NavKey

sealed class Ma3Screens(
    val key: String //Used for analytics
): NavKey, ConditionalNavKey {
    override val requiresLogin: Boolean = false

    object LoginScreen: Ma3Screens("login")

    object HomeScreen: Ma3Screens("home")

    object Routes: Ma3Screens("routes")

    class Route(val id: String): Ma3Screens("route/$id")

    class Navigation(val id: String): Ma3Screens("navigation/$id")

    object Stages: Ma3Screens("stages")

    object Settings: Ma3Screens("settings"){
        override val requiresLogin: Boolean = true
    }
}
