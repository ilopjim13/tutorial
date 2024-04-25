import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.VisualTransformation
import java.io.File
//
//@Composable
//@Preview
//fun LoginScreen(onCambiarVentana: () -> Unit) {
//    var usuario by remember { mutableStateOf("") }
//    var contra by remember { mutableStateOf("") }
//    val buttonEnabled = usuario.isNotBlank() && contra.isNotBlank()
//    var visibilidad by remember { mutableStateOf(false) }
//    var error by remember { mutableStateOf(false) }
//
//
//    MaterialTheme {
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.spacedBy(15.dp, alignment = Alignment.CenterVertically),
//            modifier = Modifier.fillMaxSize()
//        ) {
//
//            if (error) MensajeError()
//
//            Usuario(usuario) {
//                usuario = it
//            }
//            Password(contra, visibilidad, {contra = it}) {
//                visibilidad = it
//            }
//            Boton(buttonEnabled) {
//                if (!comprobrarLogin(usuario, contra)) error = true
//                else {
//                    onCambiarVentana()
//                    error = false
//                }
//                usuario = ""
//                contra = ""
//            }
//        }
//
//
//
//    }
//}
//
//fun comprobrarLogin(usuario: String, contra: String):Boolean {
//    val login = File("autenticacion.txt")
//    val datos = login.readLines()[0].split(" ")
//    return if (usuario == datos[0] && contra == datos[1]) true
//    else false
//}
//
//@Composable
//@Preview
//fun Usuario(
//    usuario:String,
//    onTeclear: (String) -> Unit
//) {
//    OutlinedTextField(
//        value = usuario,
//        onValueChange = onTeclear,
//        label = {Text("Usuario")},
//        placeholder = { Text("Introduce el usuario...")}
//    )
//}
//
//@Composable
//@Preview
//fun Boton(
//    buttonEnabled:Boolean,
//    onCambiar: () -> Unit
//) {
//    Button(onClick = onCambiar,
//        enabled = buttonEnabled) {
//        Text("Login")
//    }
//}
//
//@Composable
//@Preview
//fun Password(
//    contra:String,
//    visibilidad: Boolean,
//    onTeclear: (String) -> Unit,
//    onVisibilidad: (Boolean) -> Unit
//) {
//    OutlinedTextField(
//        value = contra,
//        onValueChange = onTeclear,
//        label = {Text("Contraseña")},
//        placeholder = { Text("Introduce la contraseña...")},
//        visualTransformation = if (visibilidad) VisualTransformation.None else PasswordVisualTransformation('*'),
//        trailingIcon = {
//            IconToggleButton(
//                checked = visibilidad,
//                onCheckedChange = onVisibilidad) {
//                Icon(
//                    imageVector = if(visibilidad) Icons.Default.VisibilityOff else Icons.Default.Visibility,
//                    contentDescription = ""
//                )
//            }
//        }
//
//    )
//}
//
//@Composable
//@Preview
//fun MensajeError() {
//    Box(
//        modifier = Modifier
//            .background(Color.Red, shape = CutCornerShape(3.dp))
//            .border(
//            border = BorderStroke(2.dp, Color.Black),
//            shape = CutCornerShape(3.dp)
//        )
//    ) { Text("Error de autenticación",
//        color = Color.White,
//        modifier = Modifier.width(270.dp)
//            .padding(15.dp)
//    ) }
//
//}
//
//
//@Composable
//@Preview
//fun UserLogin(onCambiarVentana: () -> Unit) {
//    MaterialTheme {
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.spacedBy(15.dp, alignment = Alignment.CenterVertically),
//            modifier = Modifier.fillMaxSize()
//        ) {
//            Text("Bienvenido Usuario")
//
//            Button(
//                onClick = onCambiarVentana
//            ) {
//                Text("Cerrar Cesión")
//            }
//        }
//    }
//}
//
//fun main() = application {
//    var showFirstWindow by remember { mutableStateOf(true) }
//    var showSecondWindow by remember { mutableStateOf(false) }
//
//    if(showFirstWindow) {
//        Window(onCloseRequest = ::exitApplication, title = "Login") {
//            LoginScreen {
//                showFirstWindow = false
//                showSecondWindow = true
//            }
//        }
//    }
//
//    if (showSecondWindow) {
//        Window(onCloseRequest = ::exitApplication, title = "User") {
//            UserLogin{
//                showFirstWindow = true
//                showSecondWindow = false
//            }
//        }
//
//    }
//
//
//}
