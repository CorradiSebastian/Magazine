package settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.russhwolf.settings.Settings

class ProfileScreen: Screen {

    private val settings: Settings = Settings()

    companion object{
        const val KEY_NAME = "NAME"
        const val KEY_VIP = "VIP"
    }

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        var name by remember { mutableStateOf("") }
        var isVip by remember { mutableStateOf(false) }

        Scaffold {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.weight(1f))
                OutlinedTextField(value = name, onValueChange = { name = it })
                Row(verticalAlignment = Alignment.CenterVertically

                ) {
                    Checkbox(checked = isVip, onCheckedChange = {
                        isVip = it
                    })
                    Text(text = "Eres VIP?")
                }
                Spacer(modifier = Modifier.weight(1f))
                Button( enabled = name.isNotEmpty(),
                    onClick = {
                        settings.putString(KEY_NAME, name)
                        settings.putBoolean(KEY_VIP, isVip)
                        /*settings["NAME"] = name
                        settings["isVip"] = isVip*/
                        navigator.push(    ProfileResultScreen())
                    },) {
                    Text(text = "Guardar Perfil")
                }
                Spacer(modifier = Modifier.weight(0.3f))
            }
        }
    }
}