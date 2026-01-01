package com.khan366kos.chaosinversion.transport.models.description

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DescriptionTransport(
    @SerialName("id")
    val id: String,
    @SerialName("status")
    val status: ProjectStatusTransport,
    @SerialName("title")
    val title: String,
)