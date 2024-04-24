import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.ui.text.input.VisualTransformation

@Composable
@Preview
fun App() {
    var usuario by remember { mutableStateOf("") }
    var contra by remember { mutableStateOf("") }
    var visibilidad by remember { mutableStateOf(false) }


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
                visualTransformation = if (visibilidad) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconToggleButton(checked = visibilidad, onCheckedChange = {visibilidad = it}) {
                        Icon(
                            imageVector = if(visibilidad) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                            contentDescription = ""
                        )
                    }
                }

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
