package com.khan366kos.chaosinversion.transport.models.structure

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Structure(
    @SerialName("id")
    val id: String,
)