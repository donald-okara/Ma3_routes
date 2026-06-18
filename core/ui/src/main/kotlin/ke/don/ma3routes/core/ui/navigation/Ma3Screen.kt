package ke.don.ma3routes.core.ui.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed class Ma3Screens(
    val key: String //Used for analytics
): NavKey, ConditionalNavKey {
    override val requiresLogin: Boolean = false

    @Serializable
    object LoginScreen: Ma3Screens("login")

    @Serializable
    object HomeScreen: Ma3Screens("home")

    @Serializable
    object Routes: Ma3Screens("routes")

    @Serializable
    class Route(val id: String): Ma3Screens("route/$id")

    @Serializable
    class Navigation(val id: String): Ma3Screens("navigation/$id")

    @Serializable
    object Stages: Ma3Screens("stages")

    @Serializable
    object Settings: Ma3Screens("settings"){
        override val requiresLogin: Boolean = true
    }
}
