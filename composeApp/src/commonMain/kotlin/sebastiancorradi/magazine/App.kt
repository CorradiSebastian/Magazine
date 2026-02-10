package sebastiancorradi.magazine

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import bottombar.BottomBarScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import ktorscreen.KtorScreen
import org.jetbrains.compose.resources.painterResource

import magazine.composeapp.generated.resources.Res
import magazine.composeapp.generated.resources.compose_multiplatform
import platformscreen.PlatformScreen
import settings.ProfileScreen

@Composable
@Preview
fun App() {
    MaterialTheme {
        Navigator(screen = MainScreen())
    }
}

class MainScreen: Screen{
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        var name: String by remember { mutableStateOf("") }
        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            TextField(
                value = name,
                onValueChange = { name = it },
                //label = { Text(Res.string.name) }
            )
            Spacer(modifier = Modifier.size(38.dp))
            AnimatedVisibility(name.isNotEmpty()) {
                Text(text = "hola $name")
            }
            Button(onClick = {
                navigator.push(SecondScreen())
            }) {
                Text(text = "Next")
            }
            Button(onClick = {
                navigator.push(BottomBarScreen())
            }) {
                Text(text = "BottomBar")
            }
            Spacer(modifier = Modifier.size(10.dp))
            Button(onClick = {
                navigator.push(ProfileScreen())
            }) {
                Text(text = "Navegacion con persistencia")
            }
            Spacer(modifier = Modifier.size(10.dp))
            Button(onClick = {
                navigator.push(PlatformScreen())
            }) {
                Text(text = "platform depencency screen")
            }
            Spacer(modifier = Modifier.size(10.dp))
            Button(onClick = {
                navigator.push(KtorScreen())
            }) {
                Text(text = "ktor")
            }
        }
    }

}

class SecondScreen: Screen{
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        Box(modifier = Modifier.
                fillMaxSize().
                background(Color.Red)
            ){
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                navigator
                Text("Second screen", fontSize = 24.sp)
                Button(
                    onClick = {
                        navigator.pop()
                    }
                ) {
                    Text("Back")
                }
            }
        }

    }

}