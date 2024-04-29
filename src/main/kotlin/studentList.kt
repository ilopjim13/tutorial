import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@Composable
fun MainScreen() {
    val students = remember { mutableStateListOf("Juan", "Victor", "Esther", "Jaime") }

    Surface(
        color = Color.LightGray,
        modifier = Modifier.fillMaxSize()
    ) {
        StudentList(students, {students.clear()}) {
            students.add("Miguel")
        }
    }
}



@Composable
@Preview
fun StudentList(students: List<String>, onClearAll: () -> Unit, onButtonClick: () -> Unit) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxHeight(0.8f).fillMaxSize()
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(15.dp, alignment = Alignment.CenterVertically),
                    horizontalAlignment = Alignment.CenterHorizontally) {

                    OutlinedTextField(
                        value = "",
                        onValueChange = {  },
                        label = {Text("Usuario")},
                        placeholder = { Text("Introduce el usuario...")}
                    )

                    Button(onClick = onButtonClick) {
                        Text(text = "Add new student")
                    }
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Students ${students.size}")
                    LazyColumn(
                        userScrollEnabled = true,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.height(320.dp)
                            .width(220.dp)
                            .border(2.dp, Color.Black)
                            .background(Color.White)
                    ) {

                        items(students) { message ->
                            MessageRow(message)
                        }

                    }
                    Button(onClick = onClearAll) {
                        Text(text = "Clear All")
                    }

                }
            }
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxHeight(1f)) {
                Button(onClick = onButtonClick) {
                    Text(text = "Guardar Cambios")
                }
            }

        }


    }
}



@Composable
fun StudentText(name: String) {
    Text(
        text = name,
        fontSize = 28.sp,
        modifier = Modifier.padding(10.dp)
    )
}

@Composable
fun MessageRow(message: String) {
    Text(text = message)
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        MainScreen()
    }
}