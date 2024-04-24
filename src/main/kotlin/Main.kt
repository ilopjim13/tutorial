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
fun LoginScreen(onCambiarVentana: () -> Unit) {
    var usuario by remember { mutableStateOf("") }
    var contra by remember { mutableStateOf("") }
    val buttonEnabled = usuario.isNotBlank() && contra.isNotBlank()
    var visibilidad by remember { mutableStateOf(false) }


    MaterialTheme {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(15.dp, alignment = Alignment.CenterVertically),
            modifier = Modifier.fillMaxSize()
        ) {

            Usuario(usuario) {
                usuario = it
            }
            Password(contra, visibilidad, {contra = it}) {
                visibilidad = it
            }
            Boton(buttonEnabled) {
                onCambiarVentana()
                usuario = ""
                contra = ""
            }
        }



    }
}

@Composable
@Preview
fun Usuario(
    usuario:String,
    onTeclear: (String) -> Unit
) {
    OutlinedTextField(
        value = usuario,
        onValueChange = onTeclear,
        label = {Text("Usuario")},
        placeholder = { Text("Introduce el usuario...")}
    )
}

@Composable
@Preview
fun Boton(
    buttonEnabled:Boolean,
    onCambiar: () -> Unit
) {
    Button(onClick = onCambiar,
        enabled = buttonEnabled) {
        Text("Login")
    }
}

@Composable
@Preview
fun Password(
    contra:String,
    visibilidad: Boolean,
    onTeclear: (String) -> Unit,
    onVisibilidad: (Boolean) -> Unit
) {
    OutlinedTextField(
        value = contra,
        onValueChange = onTeclear,
        label = {Text("Contraseña")},
        placeholder = { Text("Introduce la contraseña...")},
        visualTransformation = if (visibilidad) VisualTransformation.None else PasswordVisualTransformation('*'),
        trailingIcon = {
            IconToggleButton(
                checked = visibilidad,
                onCheckedChange = onVisibilidad) {
                Icon(
                    imageVector = if(visibilidad) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                    contentDescription = ""
                )
            }
        }

    )
}



//@Composable
//@Preview
//fun Login() {
//    var usuario by remember { mutableStateOf("") }
//    var contra by remember { mutableStateOf("") }
//    val buttonEnabled = usuario.isNotBlank() && contra.isNotBlank()
//    var visibilidad by remember { mutableStateOf(false) }
//
//
//    MaterialTheme {
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.spacedBy(15.dp, alignment = Alignment.CenterVertically),
//            modifier = Modifier.fillMaxSize()
//        ) {
//
//            OutlinedTextField(
//                value = usuario,
//                onValueChange = {usuario = it},
//                label = {Text("Usuario")},
//                placeholder = { Text("Introduce el usuario...")}
//            )
//            OutlinedTextField(
//                value = contra,
//                onValueChange = {contra = it},
//                label = {Text("Contraseña")},
//                placeholder = { Text("Introduce la contraseña...")},
//                visualTransformation = if (visibilidad) VisualTransformation.None else PasswordVisualTransformation('*'),
//                trailingIcon = {
//                    IconToggleButton(
//                        checked = visibilidad,
//                        onCheckedChange = {visibilidad = it}) {
//                        Icon(
//                            imageVector = if(visibilidad) Icons.Default.VisibilityOff else Icons.Default.Visibility,
//                            contentDescription = ""
//                        )
//                    }
//                }
//
//            )
//
//            Button(onClick = {
//                usuario = ""
//                contra = ""
//            },
//                enabled = buttonEnabled) {
//                Text("Login")
//            }
//        }
//
//
//
//    }
//}

@Composable
@Preview
fun UserLogin(onCambiarVentana: () -> Unit) {
    MaterialTheme {
        Column {
            Text("Bienvenido Usuario")

            Button(
                onClick = onCambiarVentana
            ) {
                Text("Cerrar Cesión")
            }
        }
    }
}

fun main() = application {
    var showFirstWindow by remember { mutableStateOf(true) }
    var showSecondWindow by remember { mutableStateOf(false) }

    if(showFirstWindow) {
        Window(onCloseRequest = ::exitApplication) {
            LoginScreen {
                showFirstWindow = false
                showSecondWindow = true
            }
        }
    }

    if (showSecondWindow) {
        Window(onCloseRequest = ::exitApplication) {
            UserLogin{
                showFirstWindow = true
                showSecondWindow = false
            }
        }

    }


}
