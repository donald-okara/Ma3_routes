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
package ke.don.ma3routes.core.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import ke.don.ma3routes.core.resources.Resources

val Inter = FontFamily(
    Font(Resources.Fonts.interRegular, FontWeight.Normal),
)

val baseline = Typography()

val Typography = Typography(
    displayLarge = baseline.displayLarge.copy(fontFamily = Inter),
    displayMedium = baseline.displayMedium.copy(fontFamily = Inter),
    displaySmall = baseline.displaySmall.copy(fontFamily = Inter),
    headlineLarge = baseline.headlineLarge.copy(fontFamily = Inter),
    headlineMedium = baseline.headlineMedium.copy(fontFamily = Inter),
    headlineSmall = baseline.headlineSmall.copy(fontFamily = Inter),
    titleLarge = baseline.titleLarge.copy(fontFamily = Inter),
    titleMedium = baseline.titleMedium.copy(fontFamily = Inter),
    titleSmall = baseline.titleSmall.copy(fontFamily = Inter),
    bodyLarge = baseline.bodyLarge.copy(fontFamily = Inter),
    bodyMedium = baseline.bodyMedium.copy(fontFamily = Inter),
    bodySmall = baseline.bodySmall.copy(fontFamily = Inter),
    labelLarge = baseline.labelLarge.copy(fontFamily = Inter),
    labelMedium = baseline.labelMedium.copy(fontFamily = Inter),
    labelSmall = baseline.labelSmall.copy(fontFamily = Inter),
)
