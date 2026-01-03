package com.khan366kos.chaosinversion.transport.models.project

import com.khan366kos.chaosinversion.transport.models.common.IBaseMessage
import com.khan366kos.chaosinversion.transport.models.common.IBaseResponse
import com.khan366kos.chaosinversion.transport.models.common.RequestError
import com.khan366kos.chaosinversion.transport.models.common.ResultResponseTransport
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReadProjectResponse(
    @SerialName("messageType")
    override val messageType: String,
    @SerialName("readProject")
    val readProject: ProjectTransport,
    override val requestId: String,
    override val result: ResultResponseTransport,
    override val errors: List<RequestError> = emptyList()
): IBaseMessage, IBaseResponse
