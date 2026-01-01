package com.khan366kos.chaosinversion.mappers

import com.khan366kos.chaosinversion.domain.models.AppContext
import com.khan366kos.chaosinversion.domain.models.project.Project
import com.khan366kos.chaosinversion.transport.models.common.ReadPaginationResponse
import com.khan366kos.chaosinversion.transport.models.project.ProjectTransport
import kotlin.math.ceil

fun Project.toTransport(): ProjectTransport = ProjectTransport(
    id = id.asString()
)

fun AppContext.toReadPaginationResponse(): ReadPaginationResponse<ProjectTransport> = ReadPaginationResponse(
    messageType = "ReadProjectResponse",
    data = projectsResponse.map { it.toTransport() },
    page = paginationRequest.page,
    size = paginationRequest.size,
    totalElements = projectsResponse.size,
    totalPages = ceil(projectsResponse.size.toDouble() / paginationRequest.size).toInt()
)