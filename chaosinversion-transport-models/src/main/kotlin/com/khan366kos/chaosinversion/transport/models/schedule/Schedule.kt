package com.khan366kos.chaosinversion.transport.models.schedule

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Schedule(
    @SerialName("id")
    val id: String,
)