package ke.don.ma3routes.core.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.AndroidUiModes.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.AndroidUiModes.UI_MODE_TYPE_NORMAL
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ThemeShowcase() {
    Ma3RoutesTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Column 1: Colors
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    ColorCard("Primary", MaterialTheme.colorScheme.primaryContainer)
                    ColorCard("Secondary", MaterialTheme.colorScheme.secondaryContainer)
                    ColorCard("Tertiary", MaterialTheme.colorScheme.tertiaryContainer)
                    ColorCard("Neutral", MaterialTheme.colorScheme.onSurfaceVariant)
                }

                // Column 2: Typography
                val typographyItems = listOf(
                    "Display Large" to MaterialTheme.typography.displayLarge,
                    "Display Medium" to MaterialTheme.typography.displayMedium,
                    "Display Small" to MaterialTheme.typography.displaySmall,
                    "Headline Large" to MaterialTheme.typography.headlineLarge,
                    "Headline Medium" to MaterialTheme.typography.headlineMedium,
                    "Headline Small" to MaterialTheme.typography.headlineSmall,
                    "Title Large" to MaterialTheme.typography.titleLarge,
                    "Title Medium" to MaterialTheme.typography.titleMedium,
                    "Title Small" to MaterialTheme.typography.titleSmall,
                    "Body Large" to MaterialTheme.typography.bodyLarge,
                    "Body Medium" to MaterialTheme.typography.bodyMedium,
                    "Label Large" to MaterialTheme.typography.labelLarge,
                    "Label Medium" to MaterialTheme.typography.labelMedium,
                    "Label Small" to MaterialTheme.typography.labelSmall,
                )

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    typographyItems.forEach { (name, style) ->
                        TypographyCard(
                            title = name,
                            font = "Inter",
                            sample = "Aa",
                            style = style
                        )
                    }
                }

                // Column 3: Components A
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    ButtonsCard()
                    ProgressCard()
                    IconButtonsCard()
                }

                // Column 4: Components B
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    SearchBarCard()
                    NavBarCard()
                    ActionIconsCard()
                }
            }
        }
    }
}

@Composable
fun ColorCard(title: String, color: Color) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        shape = RoundedCornerShape(24.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(title, style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.Bold)
                val hex = "#" + "%06X".format(color.toArgb() and 0xFFFFFF)
                Text(hex, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f))
            }
            Spacer(modifier = Modifier.height(12.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(color)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                repeat(10) { index ->
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(20.dp)
                            .background(color.copy(alpha = (index + 1) / 10f))
                    )
                }
            }
        }
    }
}

@Composable
fun TypographyCard(title: String, font: String, sample: String, style: androidx.compose.ui.text.TextStyle) {
    Card(
        modifier = Modifier.fillMaxWidth().height(120.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        shape = RoundedCornerShape(24.dp)
    ) {
        Box(modifier = Modifier.padding(16.dp).fillMaxSize()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(title, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f))
                Text(font, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f))
            }
            Text(
                text = sample,
                modifier = Modifier.align(Alignment.Center),
                style = style
            )
        }
    }
}

@Composable
fun ButtonsCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        shape = RoundedCornerShape(24.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(
                    onClick = {},
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary, contentColor = MaterialTheme.colorScheme.onPrimary),
                    contentPadding = PaddingValues(vertical = 8.dp)
                ) {
                    Text("Primary", style = MaterialTheme.typography.labelMedium)
                }
                Button(
                    onClick = {},
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary, contentColor = MaterialTheme.colorScheme.onSecondary),
                    contentPadding = PaddingValues(vertical = 8.dp)
                ) {
                    Text("Secondary", style = MaterialTheme.typography.labelMedium)
                }
            }
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(
                    onClick = {},
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.onSurface, contentColor = MaterialTheme.colorScheme.surface),
                    contentPadding = PaddingValues(vertical = 8.dp)
                ) {
                    Text("Inverted", style = MaterialTheme.typography.labelMedium)
                }
                OutlinedButton(
                    onClick = {},
                    modifier = Modifier.weight(1f),
                    contentPadding = PaddingValues(vertical = 8.dp)
                ) {
                    Text("Outlined", style = MaterialTheme.typography.labelMedium)
                }
            }
        }
    }
}

@Composable
fun ProgressCard() {
    Card(
        modifier = Modifier.fillMaxWidth().height(120.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        shape = RoundedCornerShape(24.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp).fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            repeat(3) { index ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth(if (index == 0) 0.7f else if (index == 1) 0.9f else 0.5f)
                        .height(6.dp)
                        .clip(CircleShape)
                        .background(
                            when(index) {
                                0 -> MaterialTheme.colorScheme.primary
                                1 -> MaterialTheme.colorScheme.secondary
                                else -> MaterialTheme.colorScheme.tertiary
                            }
                        )
                )
                if (index < 2) Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Composable
fun IconButtonsCard() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Card(
            modifier = Modifier.size(80.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
            shape = RoundedCornerShape(24.dp)
        ) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                IconButton(
                    onClick = {},
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(MaterialTheme.colorScheme.tertiaryContainer)
                ) {
                    Icon(Icons.Default.Edit, contentDescription = null, tint = MaterialTheme.colorScheme.onTertiaryContainer)
                }
            }
        }
        Card(
            modifier = Modifier.weight(1f).height(80.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
            shape = RoundedCornerShape(24.dp)
        ) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primaryContainer, contentColor = MaterialTheme.colorScheme.onPrimaryContainer),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(Icons.Default.Edit, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Label", style = MaterialTheme.typography.labelLarge)
                }
            }
        }
    }
}

@Composable
fun SearchBarCard() {
    Card(
        modifier = Modifier.fillMaxWidth().height(120.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        shape = RoundedCornerShape(24.dp)
    ) {
        Box(modifier = Modifier.padding(16.dp).fillMaxSize(), contentAlignment = Alignment.Center) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Search, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f), modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Search", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f))
                }
            }
        }
    }
}

@Composable
fun NavBarCard() {
    Card(
        modifier = Modifier.fillMaxWidth().height(120.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        shape = RoundedCornerShape(24.dp)
    ) {
        Box(modifier = Modifier.padding(16.dp).fillMaxSize(), contentAlignment = Alignment.Center) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .clip(RoundedCornerShape(28.dp))
                    .background(MaterialTheme.colorScheme.surface),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Home, contentDescription = null, tint = MaterialTheme.colorScheme.onPrimary, modifier = Modifier.size(20.dp))
                    }
                    Icon(Icons.Default.Search, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f), modifier = Modifier.size(20.dp))
                    Icon(Icons.Default.Person, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f), modifier = Modifier.size(20.dp))
                }
            }
        }
    }
}

@Composable
fun ActionIconsCard() {
    Card(
        modifier = Modifier.fillMaxWidth().height(120.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        shape = RoundedCornerShape(24.dp)
    ) {
        Box(modifier = Modifier.padding(16.dp).fillMaxSize(), contentAlignment = Alignment.Center) {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                repeat(4) { index ->
                    val color = when(index) {
                        0 -> MaterialTheme.colorScheme.primary
                        1 -> MaterialTheme.colorScheme.secondary
                        2 -> MaterialTheme.colorScheme.tertiary
                        else -> MaterialTheme.colorScheme.error
                    }
                    val onColor = when(index) {
                        0 -> MaterialTheme.colorScheme.onPrimary
                        1 -> MaterialTheme.colorScheme.onSecondary
                        2 -> MaterialTheme.colorScheme.onTertiary
                        else -> MaterialTheme.colorScheme.onError
                    }
                    val icon = when(index) {
                        0 -> Icons.Default.Brush
                        1 -> Icons.Default.Category
                        2 -> Icons.Default.Label
                        else -> Icons.Default.Delete
                    }
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(color),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(icon, contentDescription = null, tint = onColor, modifier = Modifier.size(20.dp))
                    }
                }
            }
        }
    }
}

@Preview(name = "Light", widthDp = 1200, heightDp = 700)
@Preview(name = "Dark", uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL, widthDp = 1200, heightDp = 700)
@Composable
fun ThemeShowcasePreview() {
    ThemeShowcase()
}
