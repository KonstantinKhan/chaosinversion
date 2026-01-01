package com.khan366kos.chaosinversion.transport.models.team

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Team(
    @SerialName("id")
    val id: String,
)