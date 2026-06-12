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

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import ke.don.ma3routes.core.ui.theme.Ma3RoutesTheme

@PreviewLightDark
@Composable
fun PreviewContent(
    content: (@Composable () -> Unit)? = {
        Text("Empty Preview", modifier = Modifier.fillMaxWidth())
    },
) {
    Ma3RoutesTheme {
        Surface {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(8.dp),
            ) {
                content?.invoke()
            }
        }
    }
}
