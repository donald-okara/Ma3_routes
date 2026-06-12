package ke.don.ma3routes.core.ui.components.card

import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable

@Composable
fun cardColorsFor(type: CardType): CardColors = when (type) {
    CardType.Outlined -> CardDefaults.outlinedCardColors()
    CardType.Filled -> CardDefaults.cardColors()
}
