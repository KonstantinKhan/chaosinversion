package com.khan366kos.chaosinversion.transport.models.company

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Company(
    @SerialName("id")
    val id: String,
) {

}