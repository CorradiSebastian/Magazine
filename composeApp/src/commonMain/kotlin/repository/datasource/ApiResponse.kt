package repository.datasource

import domain.Hero
import kotlinx.serialization.Serializable // Add this import

import kotlinx.serialization.SerialName

@Serializable
data class ApiResponse(
    val results: List<Hero>,
    @SerialName("response")
    val ok:String
)