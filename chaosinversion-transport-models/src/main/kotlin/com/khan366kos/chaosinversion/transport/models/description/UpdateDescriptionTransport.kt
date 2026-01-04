package com.khan366kos.chaosinversion.transport.models.description

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateDescriptionTransport(
    @SerialName("id")
    val id: String? = null,
    @SerialName("status")
    val status: ProjectStatusTransport? = null,
    @SerialName("title")
    val title: String? = null,
)