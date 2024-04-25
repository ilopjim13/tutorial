import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.useResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.rememberWindowState
import java.io.File
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
@Preview
fun LoginScreen(onCambiarVentana: () -> Unit) {
    var usuario by remember { mutableStateOf("") }
    var contra by remember { mutableStateOf("") }
    val buttonEnabled = usuario.isNotBlank() && contra.isNotBlank()
    var visibilidad by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf(false) }


    MaterialTheme {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(15.dp, alignment = Alignment.CenterVertically),
            modifier = Modifier.fillMaxSize()
        ) {

            if (error) MensajeError()

            Usuario(usuario) {
                usuario = it
            }
            Password(contra, visibilidad, {contra = it}) {
                visibilidad = it
            }
            Boton(buttonEnabled) {
                if (!comprobrarLogin(usuario, contra)) error = true
                else {
                    onCambiarVentana()
                    guardarLogs()
                    error = false
                }
                usuario = ""
                contra = ""
            }
        }



    }
}

fun comprobrarLogin(usuario: String, contra: String):Boolean {
    val login = File("autenticacion.txt")
    val lineas = login.readLines()
    var logeado = false

    for (linea in lineas) {
        val datos = linea.split(" ")
        if (!logeado && usuario == datos[0] && contra == datos[1]) logeado = true
    }
    return logeado
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

@Composable
@Preview
fun MensajeError() {
    Box(
        modifier = Modifier
            .background(Color.Red, shape = CircleShape)
            .border(
                border = BorderStroke(2.dp, Color.Black),
                shape = CircleShape
            )
    ) { Text("Error de autenticación",
        color = Color.White,
        textAlign = TextAlign.Center,
        modifier = Modifier.width(270.dp)
            .padding(15.dp)
    ) }

}


@Composable
@Preview
fun UserLogin(onCambiarVentana: () -> Unit) {
    MaterialTheme {

        Row (
            modifier = Modifier.padding(top = 50.dp)
        ) {

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                .fillMaxHeight()
                .border(2.dp,Color.LightGray)
                .fillMaxWidth(0.33f)
            ) {
                Text("Noticias")
            }


            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(15.dp, alignment = Alignment.CenterVertically),
                modifier = Modifier.fillMaxHeight()
                    .border(2.dp,Color.LightGray)
                    .fillMaxWidth(0.66f)
            ) {

                Text("Bienvenido Usuario")

            }

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxHeight()
                    .border(2.dp,Color.LightGray)
                    .fillMaxWidth(1f)) {
                Text("Indices")
            }

        }

        Cabecera(onCambiarVentana)


    }
}

fun crearMensaje() :String {
    val fechaHoraActual = LocalDateTime.now()
    val formateador = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")
    val fechaHoraFormateada = fechaHoraActual.format(formateador)
    return "- Inicio de sesión: $fechaHoraFormateada\n"
}


fun guardarLogs() {
    val log = File("logs.txt")
    val mensaje = crearMensaje()

    if (log.isFile) log.appendText(mensaje)
    else {
        log.writeText("Logs de inicio de sesión\n\n")
        log.appendText(mensaje)
    }
}


@Composable
fun Cabecera(onCambiarVentana: () -> Unit) {
    Box(contentAlignment = Alignment.BottomCenter,
        modifier = Modifier.fillMaxWidth(1f)
            .height(50.dp)
            .background(Color.Cyan))
    {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize()
        ) {
            Text("DAM 1", modifier = Modifier.padding(start = 15.dp))
            Button(
                onClick = onCambiarVentana,
                modifier = Modifier.padding(end = 15.dp)
            ) {
                Text("Cerrar Sesión")
            }
        }
    }
}



fun main() = application {
    var showFirstWindow by remember { mutableStateOf(true) }
    var showSecondWindow by remember { mutableStateOf(false) }
    val icon = BitmapPainter(useResource("project.jpg", ::loadImageBitmap))


    if(showFirstWindow) {
        Window(onCloseRequest = { showFirstWindow = false },
            title = "Login",
            icon = icon,
            state = rememberWindowState()) {
            LoginScreen {
                showFirstWindow = false
                showSecondWindow = true
            }
        }
    }

    if (showSecondWindow) {
        Window(onCloseRequest = { showSecondWindow = false }, title = "User", icon = icon) {
            UserLogin{
                showFirstWindow = true
                showSecondWindow = false
            }
        }

    }

    if (!showFirstWindow && !showSecondWindow) exitApplication()


}
