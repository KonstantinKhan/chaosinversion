package com.khan366kos.chaosinversion.transport.models.project

import com.khan366kos.chaosinversion.transport.models.common.IBaseMessage
import com.khan366kos.chaosinversion.transport.models.common.IBaseRequest
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateProjectRequest(
    @SerialName("requestId")
    override val requestId: String? = null,
    @SerialName("messageType")
    override val messageType: String = "CreateProjectRequest",
    @SerialName("createProject")
    val createProject: CreatableProject

) : IBaseRequest, IBaseMessage
