package platformscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import sebastiancorradi.magazine.getPlatform

class PlatformScreen: Screen {
    @Composable
    override fun Content() {
        Scaffold {
            Column (modifier = Modifier
                .fillMaxSize()
                .padding(it)) {
                val platform = getPlatform()
                val platformMessages = PlatformMessage()
                Text("Running on ${platform.name}")
                Text(platformMessages.getInitialMessage())
            }
        }

    }

}