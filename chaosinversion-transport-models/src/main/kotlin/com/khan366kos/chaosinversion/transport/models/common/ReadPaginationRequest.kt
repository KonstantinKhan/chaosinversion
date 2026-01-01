package com.khan366kos.chaosinversion.transport.models.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReadPaginationRequest(
    @SerialName("messageType")
    override val messageType: String = "Unknown messageType",
    @SerialName("page")
    val page: Int? = null,
    @SerialName("size")
    val size: Int? = null,
): IBaseMessage