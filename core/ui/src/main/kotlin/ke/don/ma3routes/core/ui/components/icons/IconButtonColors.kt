package ke.don.ma3routes.core.ui.components.icons

import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import ke.don.ma3routes.core.ui.components.buttons.ButtonType
import ke.don.ma3routes.core.ui.theme.LocalThemeProvider

/**
 * Returns the [IconButtonColors] for a given [ke.don.ma3routes.core.ui.components.buttons.ButtonType].
 * In light theme, uses the actual colors (Primary, Secondary, etc.).
 * In dark theme, uses the container colors (PrimaryContainer, SecondaryContainer, etc.).
 */
@Composable
fun iconButtonColorsFor(type: ButtonType): IconButtonColors {
    val isDark = LocalThemeProvider.current

    return when (type) {
        ButtonType.Primary -> if (isDark) {
            IconButtonDefaults.iconButtonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                disabledContainerColor = MaterialTheme.colorScheme.primaryContainer.copy(
                    alpha = 0.12f,
                ),
                disabledContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
            )
        } else {
            IconButtonDefaults.iconButtonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.12f),
                disabledContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
            )
        }

        ButtonType.Secondary -> IconButtonDefaults.iconButtonColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.onSecondary,
            disabledContainerColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.12f),
            disabledContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
        )

        ButtonType.Tertiary -> if (isDark) {
            IconButtonDefaults.iconButtonColors(
                containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                disabledContainerColor = MaterialTheme.colorScheme.tertiaryContainer.copy(
                    alpha = 0.12f,
                ),
                disabledContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
            )
        } else {
            IconButtonDefaults.iconButtonColors(
                containerColor = MaterialTheme.colorScheme.tertiary,
                contentColor = MaterialTheme.colorScheme.onTertiary,
                disabledContainerColor = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.12f),
                disabledContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
            )
        }

        ButtonType.Danger -> if (isDark) {
            IconButtonDefaults.iconButtonColors(
                containerColor = MaterialTheme.colorScheme.errorContainer,
                contentColor = MaterialTheme.colorScheme.onErrorContainer,
                disabledContainerColor = MaterialTheme.colorScheme.errorContainer.copy(
                    alpha = 0.12f,
                ),
                disabledContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
            )
        } else {
            IconButtonDefaults.iconButtonColors(
                containerColor = MaterialTheme.colorScheme.error,
                contentColor = MaterialTheme.colorScheme.onError,
                disabledContainerColor = MaterialTheme.colorScheme.error.copy(alpha = 0.12f),
                disabledContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
            )
        }

        ButtonType.Outlined -> IconButtonDefaults.iconButtonColors(
            containerColor = Color.Transparent,
            contentColor = if (isDark) {
                MaterialTheme.colorScheme.primaryContainer
            } else {
                MaterialTheme.colorScheme.primary
            },
            disabledContainerColor = Color.Transparent,
            disabledContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
        )
    }
}
