package ke.don.ma3routes.features.profile.screens

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ke.don.ma3routes.core.ui.components.Ma3TopBar

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
) {

}

@Composable
fun SettingsScreenComponent(
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            Ma3TopBar(
                title = "Settings",
                onBack = {}
            )
        }
    ) { paddingValues ->

    }
}

