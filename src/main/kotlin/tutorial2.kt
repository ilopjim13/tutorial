import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.singleWindowApplication
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.loadSvgPainter
import androidx.compose.ui.res.loadXmlImageVector
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.singleWindowApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.xml.sax.InputSource
import java.io.File
import java.io.IOException
import java.net.URL
import androidx.compose.foundation.Canvas
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.withSave
import androidx.compose.ui.res.useResource


@Composable
@Preview
fun Imagen() = singleWindowApplication {
    Image(
        painter = painterResource("sample.png"),
        alignment = Alignment.Center,
        contentDescription = "Sample",
        modifier = Modifier.fillMaxSize()
    )
}


@Composable
@Preview
fun Imagenes() = singleWindowApplication {
    val density = LocalDensity.current
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        if (File("src\\main\\resources\\sample.png").isFile) {
            AsyncImage(
                load = { loadImageBitmap(File("src\\main\\resources\\sample.png")) },
                painterFor = { remember { BitmapPainter(it) } },
                contentDescription = "Sample",
                modifier = Modifier.width(200.dp)
            )
        }

        AsyncImage(
            load = { loadSvgPainter("https://github.com/JetBrains/compose-multiplatform/raw/master/artwork/idea-logo.svg", density) },
            painterFor = { it },
            contentDescription = "Idea logo",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.width(200.dp)
        )
        if (File("src\\main\\resources\\compose-logo.xml").isFile) {
            AsyncImage(
                load = { loadXmlImageVector(File("src\\main\\resources\\compose-logo.xml"), density) },
                painterFor = { rememberVectorPainter(it) },
                contentDescription = "Compose logo",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.width(200.dp)
            )
        }

    }
}

@Composable
fun <T> AsyncImage(
    load: suspend () -> T,
    painterFor: @Composable (T) -> Painter,
    contentDescription: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit,
) {
    val image: T? by produceState<T?>(null) {
        value = withContext(Dispatchers.IO) {
            try {
                load()
            } catch (e: IOException) {
                // instead of printing to console, you can also write this to log,
                // or show some error placeholder
                e.printStackTrace()
                null
            }
        }
    }

    if (image != null) {
        Image(
            painter = painterFor(image!!),
            contentDescription = contentDescription,
            contentScale = contentScale,
            modifier = modifier
        )
    }
}

/* Loading from file with java.io API */

fun loadImageBitmap(file: File): ImageBitmap =
    file.inputStream().buffered().use(::loadImageBitmap)

fun loadSvgPainter(file: File, density: Density): Painter =
    file.inputStream().buffered().use { loadSvgPainter(it, density) }

fun loadXmlImageVector(file: File, density: Density): ImageVector =
    file.inputStream().buffered().use { loadXmlImageVector(InputSource(it), density) }

/* Loading from network with java.net API */

fun loadImageBitmap(url: String): ImageBitmap =
    URL(url).openStream().buffered().use(::loadImageBitmap)

fun loadSvgPainter(url: String, density: Density): Painter =
    URL(url).openStream().buffered().use { loadSvgPainter(it, density) }

fun loadXmlImageVector(url: String, density: Density): ImageVector =
    URL(url).openStream().buffered().use { loadXmlImageVector(InputSource(it), density) }

/* Loading from network with Ktor client API (https://ktor.io/docs/client.html). */

/*

suspend fun loadImageBitmap(url: String): ImageBitmap =
    urlStream(url).use(::loadImageBitmap)

suspend fun loadSvgPainter(url: String, density: Density): Painter =
    urlStream(url).use { loadSvgPainter(it, density) }

suspend fun loadXmlImageVector(url: String, density: Density): ImageVector =
    urlStream(url).use { loadXmlImageVector(InputSource(it), density) }

@OptIn(KtorExperimentalAPI::class)
private suspend fun urlStream(url: String) = HttpClient(CIO).use {
    ByteArrayInputStream(it.get(url))
}

 */





fun main() = singleWindowApplication {
    val density = LocalDensity.current // to calculate the intrinsic size of vector images (SVG, XML)

    val sample = remember {
        useResource("src\\main\\resources\\sample.png", ::loadImageBitmap)
    }
    val ideaLogo = remember {
        useResource("src\\main\\resources\\idea-logo.svg") { loadSvgPainter(it, density) }
    }
    val composeLogo = rememberVectorPainter(
        remember {
            useResource("src\\main\\resources\\compose-logo.xml") { loadXmlImageVector(InputSource(it), density) }
        }
    )

    Canvas(
        modifier = Modifier.fillMaxSize()
    ) {
        drawIntoCanvas { canvas ->
            canvas.withSave {
                canvas.drawImage(sample, Offset.Zero, Paint())
                canvas.translate(sample.width.toFloat(), 0f)
                with(ideaLogo) {
                    draw(ideaLogo.intrinsicSize)
                }
                canvas.translate(ideaLogo.intrinsicSize.width, 0f)
                with(composeLogo) {
                    draw(Size(100f, 100f))
                }
            }
        }
    }
}
