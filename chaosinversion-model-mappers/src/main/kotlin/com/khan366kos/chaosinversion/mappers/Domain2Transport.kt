package com.khan366kos.chaosinversion.mappers

import com.khan366kos.chaosinversion.domain.models.AppContext
import com.khan366kos.chaosinversion.domain.models.common.ProjectStatus
import com.khan366kos.chaosinversion.domain.models.description.Description
import com.khan366kos.chaosinversion.domain.models.project.Project
import com.khan366kos.chaosinversion.transport.models.common.ReadPaginationResponse
import com.khan366kos.chaosinversion.transport.models.description.DescriptionTransport
import com.khan366kos.chaosinversion.transport.models.description.ProjectStatusTransport
import com.khan366kos.chaosinversion.transport.models.project.ProjectTransport
import kotlin.math.ceil

fun Project.toTransport(): ProjectTransport = ProjectTransport(
    id = id.asString(),
    description = description.toTransport(),
)

fun Description.toTransport(): DescriptionTransport = DescriptionTransport(
    id = id.asString(),
    status = when(status) {
        ProjectStatus.ACTIVE -> ProjectStatusTransport.ACTIVE
        ProjectStatus.PENDING -> ProjectStatusTransport.PENDING
        ProjectStatus.UNKNOWN -> ProjectStatusTransport.UNKNOWN
    },
    title = title.value
)

fun AppContext.toReadPaginationResponse(): ReadPaginationResponse<ProjectTransport> = ReadPaginationResponse(
    messageType = "ReadProjectResponse",
    data = projectsResponse.map { it.toTransport() },
    page = paginationRequest.page,
    size = paginationRequest.size,
    totalElements = projectsResponse.size,
    totalPages = ceil(projectsResponse.size.toDouble() / paginationRequest.size).toInt()
)