package domain

import kotlinx.serialization.Serializable

@Serializable
public data class Hero(
    val id: String,
    val name: String
)