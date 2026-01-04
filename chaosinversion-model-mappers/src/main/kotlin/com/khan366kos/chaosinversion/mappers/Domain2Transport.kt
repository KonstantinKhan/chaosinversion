package com.khan366kos.chaosinversion.mappers

import com.khan366kos.chaosinversion.domain.models.AppContext
import com.khan366kos.chaosinversion.domain.models.common.Error
import com.khan366kos.chaosinversion.domain.models.common.Id
import com.khan366kos.chaosinversion.domain.models.common.ProjectStatus
import com.khan366kos.chaosinversion.domain.models.common.Title
import com.khan366kos.chaosinversion.domain.models.description.Description
import com.khan366kos.chaosinversion.domain.models.project.Project
import com.khan366kos.chaosinversion.transport.models.common.ReadPaginationResponse
import com.khan366kos.chaosinversion.transport.models.common.RequestError
import com.khan366kos.chaosinversion.transport.models.common.ResultResponseTransport
import com.khan366kos.chaosinversion.transport.models.description.DescriptionTransport
import com.khan366kos.chaosinversion.transport.models.description.ProjectStatusTransport
import com.khan366kos.chaosinversion.transport.models.project.CreateProjectResponse
import com.khan366kos.chaosinversion.transport.models.project.ProjectTransport
import com.khan366kos.chaosinversion.transport.models.project.ReadProjectResponse
import com.khan366kos.chaosinversion.transport.models.project.UpdateProjectResponse
import kotlin.math.ceil

fun AppContext.toReadProjectsResponse(): ReadPaginationResponse<ProjectTransport> = ReadPaginationResponse(
    messageType = "ReadProjectsResponseWithPagination",
    requestId = requestId.asString(),
    result = if (errors.isEmpty()) ResultResponseTransport.SUCCESS else ResultResponseTransport.ERROR,
    errors = errors.takeIf { it.isNotEmpty() }?.map { it.toTransport() } ?: emptyList(),
    data = projectsResponse.map { it.toTransport() },
    page = paginationRequest.page,
    size = paginationRequest.size,
    totalElements = projectsResponse.size,
    totalPages = ceil(projectsResponse.size.toDouble() / paginationRequest.size).toInt()
)

fun AppContext.toReadProjectResponse(): ReadProjectResponse = ReadProjectResponse(
    messageType = "ReadProjectResponse",
    requestId = requestId.asString(),
    result = if (errors.isEmpty()) ResultResponseTransport.SUCCESS else ResultResponseTransport.ERROR,
    errors = errors.takeIf { it.isNotEmpty() }?.map { it.toTransport() } ?: emptyList(),
    readProject = readProject.toTransport()
)

fun AppContext.toCreateProjectResponse(): CreateProjectResponse = CreateProjectResponse(
    messageType = "CreateProjectResponse",
    requestId = requestId.asString(),
    result = if (errors.isEmpty()) ResultResponseTransport.SUCCESS else ResultResponseTransport.ERROR,
    errors = errors.takeIf { it.isNotEmpty() }?.map { it.toTransport() } ?: emptyList(),
    createProject = createProject.toTransport()
)
fun AppContext.toUpdateProjectResponse(): UpdateProjectResponse = UpdateProjectResponse(
    messageType = "UpdateProjectResponse",
    requestId = requestId.asString(),
    result = if (errors.isEmpty()) ResultResponseTransport.SUCCESS else ResultResponseTransport.ERROR,
    errors = errors.takeIf { it.isNotEmpty() }?.map { it.toTransport() } ?: emptyList(),
    updateProject = projectResponse.takeIf { it != Project() }?.toTransport()
)

fun Project.toTransport() = ProjectTransport(
    id = id.asString(),
    description = description.toTransport(),
)

fun Description.toTransport() = DescriptionTransport(
    id = id.takeIf { it != Id.NONE }?.asString(),
    status = when (status) {
        ProjectStatus.ACTIVE -> ProjectStatusTransport.ACTIVE
        ProjectStatus.PENDING -> ProjectStatusTransport.PENDING
        ProjectStatus.UNKNOWN -> ProjectStatusTransport.UNKNOWN
    },
    title = title.takeIf { it != Title.NONE }?.asString()
)

fun Error.toTransport() = RequestError(
    message = message,
    field = field,
)