package com.khan366kos.chaosinversion.transport.models.project

import com.khan366kos.chaosinversion.transport.models.common.IBaseMessage
import com.khan366kos.chaosinversion.transport.models.common.IBaseResponse
import com.khan366kos.chaosinversion.transport.models.common.RequestError
import com.khan366kos.chaosinversion.transport.models.common.ResultResponseTransport
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateProjectResponse(
    @SerialName("messageType")
    override val messageType: String,
    @SerialName("requestId")
    override val requestId: String,
    @SerialName("result")
    override val result: ResultResponseTransport,
    @SerialName("errors")
    override val errors: List<RequestError>,
    @SerialName("createProject")
    val createProject: ProjectTransport,

): IBaseMessage, IBaseResponse
