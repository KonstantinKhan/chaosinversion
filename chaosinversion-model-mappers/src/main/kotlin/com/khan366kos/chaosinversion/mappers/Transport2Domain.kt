package com.khan366kos.chaosinversion.mappers

import com.khan366kos.chaosinversion.domain.models.AppContext
import com.khan366kos.chaosinversion.domain.models.common.Id
import com.khan366kos.chaosinversion.domain.models.common.Pagination
import com.khan366kos.chaosinversion.domain.models.common.ProjectStatus
import com.khan366kos.chaosinversion.domain.models.common.Title
import com.khan366kos.chaosinversion.domain.models.description.Description
import com.khan366kos.chaosinversion.domain.models.project.Project
import com.khan366kos.chaosinversion.transport.models.common.ReadPaginationRequest
import com.khan366kos.chaosinversion.transport.models.description.ProjectStatusTransport
import com.khan366kos.chaosinversion.transport.models.project.CreatableProject
import com.khan366kos.chaosinversion.transport.models.project.CreateProjectRequest
import com.khan366kos.chaosinversion.transport.models.project.DeleteProjectRequest
import com.khan366kos.chaosinversion.transport.models.project.ProjectTransport
import com.khan366kos.chaosinversion.transport.models.project.ReadProjectRequest
import com.khan366kos.chaosinversion.transport.models.project.UpdatableProject
import com.khan366kos.chaosinversion.transport.models.project.UpdateProjectRequest
import java.util.UUID

fun AppContext.setQuery(request: ReadPaginationRequest) = apply {
    requestId = request.requestId?.let { Id(it) } ?: Id(UUID.randomUUID())
    paginationRequest = request.toDomain()
}

fun AppContext.setQuery(request: CreateProjectRequest) = apply {
    requestId = request.requestId?.let { Id(it) } ?: Id(UUID.randomUUID())
    createProject = request.createProject.toDomain()
}

fun AppContext.setQuery(request: ReadProjectRequest) = apply {
    requestId = request.requestId?.let { Id(it) } ?: Id(UUID.randomUUID())
    readProjectId = request.projectId?.let { Id(it) } ?: Id.NONE
}

fun AppContext.setQuery(request: UpdateProjectRequest) = apply {
    requestId = request.requestId?.let { Id(it) } ?: Id(UUID.randomUUID())
    updateProject = request.updateProject.toDomain()
}

fun AppContext.setQuery(request: DeleteProjectRequest) = apply {
    requestId = request.requestId?.let { Id(it) } ?: Id(UUID.randomUUID())
    deleteProjectId = Id(request.projectId)
}

fun CreatableProject.toDomain() = Project(
    description = Description(
        title = Title(description.title ?: "")
    ),
)

fun ReadPaginationRequest.toDomain(): Pagination = Pagination(
    page = page ?: 0,
    size = size ?: 10,
)

fun UpdatableProject.toDomain(): Project = Project(
    id = Id(id),
    description = Description(
        title = Title(description?.title ?: ""),
        status = when (description?.status) {
            ProjectStatusTransport.ACTIVE -> ProjectStatus.ACTIVE
            ProjectStatusTransport.PENDING -> ProjectStatus.PENDING
            else -> ProjectStatus.UNKNOWN
        }
    ),
)

fun ProjectTransport.toDomain(): Project = Project(
    id = Id(id)
)

