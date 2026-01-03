package com.khan366kos.chaosinversion.transport.models.project

import com.khan366kos.chaosinversion.transport.models.common.IBaseMessage
import com.khan366kos.chaosinversion.transport.models.common.IBaseRequest
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReadProjectRequest(
    @SerialName("messageType")
    override val messageType: String = "readProject",
    @SerialName("projectId")
    val projectId: String? = null,
    @SerialName("requestId")
    override val requestId: String? = null,
): IBaseMessage, IBaseRequest