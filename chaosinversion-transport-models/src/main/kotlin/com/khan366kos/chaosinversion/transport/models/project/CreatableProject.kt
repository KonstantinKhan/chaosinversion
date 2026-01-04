package com.khan366kos.chaosinversion.transport.models.project

import com.khan366kos.chaosinversion.transport.models.description.DescriptionTransport
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreatableProject(
    @SerialName("description")
    val description: DescriptionTransport
)
