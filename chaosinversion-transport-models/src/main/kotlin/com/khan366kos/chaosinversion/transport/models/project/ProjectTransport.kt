package com.khan366kos.chaosinversion.transport.models.project

import com.khan366kos.chaosinversion.transport.models.description.DescriptionTransport
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProjectTransport(
    @SerialName("id")
    val id: String,
    @SerialName("description")
    val description: DescriptionTransport,
    @SerialName("teamId")
    val teamId: String? = null,
    @SerialName("companyId")
    val companyId: String? = null,
    @SerialName("scheduleId")
    val scheduleId: String? = null,
    @SerialName("financesId")
    val financesId: String? = null,
    @SerialName("structureId")
    val structureId: String? = null,
)