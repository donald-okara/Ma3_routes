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
package ke.don.ma3routes.core.ui.theme.preview

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.AndroidUiModes.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.AndroidUiModes.UI_MODE_TYPE_NORMAL
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ke.don.ma3routes.core.ui.components.buttons.ButtonType
import ke.don.ma3routes.core.ui.components.buttons.Ma3Button
import ke.don.ma3routes.core.ui.components.card.CardType
import ke.don.ma3routes.core.ui.components.card.Ma3Card
import ke.don.ma3routes.core.ui.components.icons.Ma3Icon
import ke.don.ma3routes.core.ui.components.icons.Ma3IconButton
import ke.don.ma3routes.core.ui.theme.Ma3RoutesTheme

@Composable
fun ThemeShowcase() {
    Ma3RoutesTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .horizontalScroll(rememberScrollState())
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                // Column 1: Colors
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    ColorCard("Primary Container", MaterialTheme.colorScheme.primaryContainer)
                    ColorCard("Secondary Container", MaterialTheme.colorScheme.secondaryContainer)
                    ColorCard("Tertiary Container", MaterialTheme.colorScheme.tertiaryContainer)
                    ColorCard("Inverse Container", MaterialTheme.colorScheme.onSurfaceVariant)
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
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    typographyItems.forEach { (name, style) ->
                        TypographyCard(
                            title = name,
                            font = "Inter",
                            sample = "Aa",
                            style = style,
                        )
                    }
                }

                // Column 3: Components A
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    ButtonsCard()
                    IconButtonsCard()
                }

                // Column 4: Components B
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    SearchBarCard()
                    ActionIconsCard()
                }

                // Column 5: Cards
                CardsShowcase()
            }
        }
    }
}

@Composable
fun ColorCard(title: String, color: Color) {
    Ma3Card(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    title,
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                )
                val hex = "#" + "%06X".format(color.toArgb() and 0xFFFFFF)
                Text(
                    hex,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(color),
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(2.dp),
            ) {
                repeat(10) { index ->
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(20.dp)
                            .background(color.copy(alpha = (index + 1) / 10f)),
                    )
                }
            }
        }
    }
}

@Composable
fun TypographyCard(
    title: String,
    font: String,
    sample: String,
    style: TextStyle,
    type: CardType = CardType.Filled,
) {
    Ma3Card(
        modifier = Modifier.fillMaxWidth().height(120.dp),
        type = type,
    ) {
        Box(modifier = Modifier.padding(16.dp).fillMaxSize()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    title,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                )
                Text(
                    font,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                )
            }
            Text(
                text = sample,
                modifier = Modifier.align(Alignment.Center),
                style = style,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
    }
}

@Composable
fun RowScope.CardsShowcase() {
    Column(
        modifier = Modifier.weight(1f),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        val text = "Aa" to MaterialTheme.typography.displayLarge

        CardType.entries.forEach { type ->
            TypographyCard(
                font = "Inter",
                title = type.name,
                sample = text.first,
                style = text.second,
                type = type,
            )
        }
    }
}

@Composable
fun ButtonsCard() {
    Ma3Card(
        modifier = Modifier.fillMaxWidth(),
    ) {
        FlowRow(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
        ) {
            ButtonType.entries.forEach { type ->
                Ma3Button(
                    text = type.name,
                    type = type,
                    onClick = {},
                )
            }
        }
    }
}

@Composable
fun IconButtonsCard() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Ma3Card(
            modifier = Modifier.size(80.dp),
        ) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Ma3IconButton(
                    onClick = {},
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "",
                    )
                }
            }
        }
        Ma3Card(
            modifier = Modifier.weight(1f).height(80.dp),
        ) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Ma3Button(
                    onClick = {},
                ) {
                    Icon(
                        Icons.Default.Edit,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp),
                    )
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
        shape = RoundedCornerShape(24.dp),
    ) {
        Box(modifier = Modifier.padding(16.dp).fillMaxSize(), contentAlignment = Alignment.Center) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.CenterStart,
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                        modifier = Modifier.size(20.dp),
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        "Search",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                    )
                }
            }
        }
    }
}

@Composable
fun ActionIconsCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        shape = RoundedCornerShape(24.dp),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(
                "Action Buttons",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
            )
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                ButtonType.entries.forEach { type ->
                    Ma3IconButton(
                        type = type,
                        onClick = {},
                    ) {
                        Icon(
                            imageVector = when (type) {
                                ButtonType.Primary -> Icons.Default.Add
                                ButtonType.Secondary -> Icons.Default.Share
                                ButtonType.Tertiary -> Icons.Default.Favorite
                                ButtonType.Danger -> Icons.Default.Delete
                                ButtonType.Outlined -> Icons.Default.MoreVert
                            },
                            contentDescription = null,
                            modifier = Modifier.size(20.dp),
                        )
                    }
                }
            }

            Text(
                "Static Icons",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
            )
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                val iconItems = listOf(
                    Triple(
                        Icons.Default.DirectionsBus,
                        MaterialTheme.colorScheme.primary,
                        MaterialTheme.colorScheme.onPrimary,
                    ),
                    Triple(
                        Icons.Default.Place,
                        MaterialTheme.colorScheme.secondary,
                        MaterialTheme.colorScheme.onSecondary,
                    ),
                    Triple(
                        Icons.Default.Schedule,
                        MaterialTheme.colorScheme.tertiary,
                        MaterialTheme.colorScheme.onTertiary,
                    ),
                    Triple(
                        Icons.Default.Warning,
                        MaterialTheme.colorScheme.error,
                        MaterialTheme.colorScheme.onError,
                    ),
                    Triple(
                        Icons.Default.Edit,
                        MaterialTheme.colorScheme.surface,
                        MaterialTheme.colorScheme.onSurface,
                    ),
                )

                iconItems.forEach { (icon, color, onColor) ->
                    Ma3Icon(
                        icon = icon,
                        color = color,
                        onColor = onColor,
                    )
                }
            }
        }
    }
}

@Preview(name = "Light", widthDp = 1200, heightDp = 700)
@Preview(
    name = "Dark",
    uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL,
    widthDp = 1200,
    heightDp = 700,
)
@Composable
fun ThemeShowcasePreview() {
    ThemeShowcase()
}
