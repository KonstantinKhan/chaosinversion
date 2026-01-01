package com.khan366kos.chaosinversion.transport.models.description

import kotlinx.serialization.Serializable

@Serializable
enum class ProjectStatusTransport {
    UNKNOWN,
    ACTIVE,
    PENDING,
}