package com.khan366kos.chaosinversion.transport.models.finances

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Finances(
    @SerialName("id")
    val id: String,
)