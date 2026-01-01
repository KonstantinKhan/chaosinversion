package com.khan366kos.chaosinversion.project.service

import com.khan366kos.chaosinversion.domain.models.AppContext
import com.khan366kos.chaosinversion.domain.models.common.Id
import com.khan366kos.chaosinversion.domain.models.common.Name
import com.khan366kos.chaosinversion.domain.models.common.Pagination
import com.khan366kos.chaosinversion.domain.models.db.DbReadProjectRequest
import com.khan366kos.chaosinversion.domain.models.project.Project
import com.khan366kos.chaosinversion.domain.models.project.repository.IProjectRepository
import com.khan366kos.chaosinversion.mappers.setQuery
import com.khan366kos.chaosinversion.mappers.toReadPaginationResponse
import com.khan366kos.chaosinversion.transport.models.common.ReadPaginationRequest
import com.khan366kos.chaosinversion.transport.models.common.ReadPaginationResponse
import com.khan366kos.chaosinversion.transport.models.project.ProjectTransport

class ProjectService(val repo: IProjectRepository) {
    suspend fun readProject(
        context: AppContext,
        request: ReadPaginationRequest
    ): ReadPaginationResponse<ProjectTransport> {
        context.setQuery(request)
        val result = repo.projects(DbReadProjectRequest(
            Pagination(request.page ?: 0, request.size ?: 10)
        ))

        context.apply {
            projectsResponse = result.result
        }

        return context.toReadPaginationResponse()
    }
}