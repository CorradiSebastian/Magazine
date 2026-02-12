package repository.datasource

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.HttpHeaders.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

// https://www.superheroapi.com/api.php/c5ba0c4ddec16769f7fb7f875eeed4d1/search/super
object NetworkDataSource {
    val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            },
                //contentType = io.ktor.http.ContentType.Application.Json)
                contentType = io.ktor.http.ContentType.Any
            )
        }
    }
}
