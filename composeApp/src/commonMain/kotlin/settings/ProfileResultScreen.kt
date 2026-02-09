package settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.russhwolf.settings.Settings

class ProfileResultScreen: Screen {

    private val settings = Settings()


    @Composable
    override fun Content() {
        val isVip = settings.getBoolean(ProfileScreen.KEY_VIP, false)
        val backgroundColor = if (isVip){Color.Yellow} else {Color.White}
        val name = settings.getString(ProfileScreen.KEY_NAME, "")

        val navigator = LocalNavigator.currentOrThrow
        Column(modifier = Modifier.fillMaxSize()
                           .background(backgroundColor),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Text(text = "Bienvenid@ $name", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.size(10.dp))
            Button(onClick = {
                settings.clear()
                //settings.remove(ProfileScreen.KEY_NAME)
                navigator.pop()
            }) {
                Text(text = "volver y borrar datos")
            }
        }
    }
}