package com.khan366kos.chaosinversion.transport.models.description

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Description(
    @SerialName("id")
    val id: String,
    @SerialName("status")
    val status: Status,
)