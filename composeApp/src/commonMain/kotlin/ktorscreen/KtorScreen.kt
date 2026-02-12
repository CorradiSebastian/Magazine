package ktorscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
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
import cafe.adriel.voyager.core.screen.Screen
import domain.Hero
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import repository.datasource.NetworkDataSource.httpClient
import io.ktor.client.statement.bodyAsText
import repository.datasource.ApiResponse


class KtorScreen: Screen {
    @Composable
    override fun Content() {
        val showContent by remember { mutableStateOf(true)}
        var superHeroName by remember { mutableStateOf("") }
        val url = "https://www.superheroapi.com/api.php/c5ba0c4ddec16769f7fb7f875eeed4d1/search/"
        var response by remember { mutableStateOf("Esperando...") }
        val superHeroList = remember { mutableStateOf(listOf<Hero>()) }
        Scaffold {
        Column(modifier = Modifier.fillMaxSize().padding(it),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally)
        {
            Row(modifier = Modifier.fillMaxWidth(),) {
                TextField(value = superHeroName, onValueChange = { superHeroName = it })
                Button(onClick = {
                    //response = "Cargando..."
                    CoroutineScope(Dispatchers.IO).launch {
                        getSuperHeroList(superHeroName, onSuccessResponse = {
                            //response = it.toString()
                            superHeroList.value = it
                        })
                    }
                }) {
                    Text("Cargar")
                }
            }
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(superHeroList.value.size) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(superHeroList.value[it].name)
                    }
                    HorizontalDivider()
                }
            }
            Text(response)
        }
            }

    }
}

fun getSuperHeroList(superHeroName: String, onSuccessResponse: (List<Hero>) -> Unit){
    val base = "https://www.superheroapi.com/api.php/c5ba0c4ddec16769f7fb7f875eeed4d1/search/"

    if (superHeroName.isBlank())
        return
    val url = base + superHeroName

    CoroutineScope(Dispatchers.IO).launch {
        val response = httpClient.get(url).body<ApiResponse>()
        println("results: ${response.results}")
        onSuccessResponse(response.results)
    }
}