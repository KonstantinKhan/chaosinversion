package com.khan366kos.chaosinversion.project.service

import com.khan366kos.chaosinversion.domain.models.AppContext
import com.khan366kos.chaosinversion.domain.models.common.Pagination
import com.khan366kos.chaosinversion.domain.models.project.repository.DbReadProjectsRequest
import com.khan366kos.chaosinversion.domain.models.project.repository.IProjectRepository
import com.khan366kos.chaosinversion.mappers.setQuery
import com.khan366kos.chaosinversion.mappers.toReadProjectsResponse
import com.khan366kos.chaosinversion.transport.models.common.ReadPaginationRequest
import com.khan366kos.chaosinversion.transport.models.common.ReadPaginationResponse
import com.khan366kos.chaosinversion.transport.models.project.ProjectTransport

class ProjectService(val repo: IProjectRepository) {
    suspend fun readProject(
        context: AppContext,
        request: ReadPaginationRequest
    ): ReadPaginationResponse<ProjectTransport> {
        context.setQuery(request)
        val result = repo.projects(DbReadProjectsRequest(
            Pagination(request.page ?: 0, request.size ?: 10)
        ))

        context.apply {
            projectsResponse = result.result
        }

        return context.toReadProjectsResponse()
    }
}