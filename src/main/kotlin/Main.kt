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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.*
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@Composable
@Preview
fun App() {
    var usuario by remember { mutableStateOf("") }
    var contra by remember { mutableStateOf("") }

    MaterialTheme {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(15.dp, alignment = Alignment.CenterVertically),
            modifier = Modifier.fillMaxSize()
        ) {

            OutlinedTextField(
                value = usuario,
                onValueChange = {usuario = it},
                label = {Text("Usuario")}
            )
            OutlinedTextField(
                value = contra,
                onValueChange = {contra = it},
                label = {Text("Contraseña")},
                visualTransformation = PasswordVisualTransformation()
            )

            Button(onClick = {
                usuario = ""
                contra = ""
            }) {
                Text("Login")
            }
        }



    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
