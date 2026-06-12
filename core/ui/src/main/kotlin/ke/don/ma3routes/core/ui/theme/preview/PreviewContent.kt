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
    content: (@Composable () -> Unit)? = { Text("Empty Preview", modifier = Modifier.fillMaxWidth()) }
){
    Ma3RoutesTheme {
        Surface {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(8.dp)
            ){
                content?.invoke()
            }
        }
    }
}