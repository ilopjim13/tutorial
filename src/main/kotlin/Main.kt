import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@OptIn(ExperimentalComposeUiApi::class)
@Composable
@Preview
fun App() {
    var text by remember { mutableStateOf("Login") }
    var consumedText by remember { mutableStateOf(0) }

    MaterialTheme {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            //verticalArrangement = Arrangement.spacedBy(50.dp),
            modifier = Modifier.fillMaxSize()
        ) {

            Box(
                modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 40.dp)
                    .border(2.dp, Color.LightGray)
                    .width(200.dp)
            ) {
                Text("", modifier = Modifier.padding(15.dp))
            }

            Box (
                modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 55.dp)
                    .border(2.dp, Color.LightGray)
                    .width(200.dp)
            ) {
                Text("", modifier = Modifier.padding(15.dp))
            }

            Button(onClick = {

            }) {
                Text(text)
            }
        }



    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
