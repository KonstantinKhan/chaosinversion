package com.khan366kos.chaosinversion.transport.models.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestError(
    @SerialName("message")
    val message: String,
    @SerialName("field")
    val field: String = "",
)
