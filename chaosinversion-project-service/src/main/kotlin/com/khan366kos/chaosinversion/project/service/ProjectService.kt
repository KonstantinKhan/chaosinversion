package com.khan366kos.chaosinversion.project.service

import com.khan366kos.chaosinversion.domain.models.AppContext
import com.khan366kos.chaosinversion.domain.models.common.Pagination
import com.khan366kos.chaosinversion.domain.models.project.repository.DbProjectRequest
import com.khan366kos.chaosinversion.domain.models.project.repository.DbProjectsRequest
import com.khan366kos.chaosinversion.domain.models.project.repository.IProjectRepository
import com.khan366kos.chaosinversion.mappers.setQuery
import com.khan366kos.chaosinversion.mappers.toCreateProjectResponse
import com.khan366kos.chaosinversion.mappers.toDomain
import com.khan366kos.chaosinversion.mappers.toReadProjectsResponse
import com.khan366kos.chaosinversion.transport.models.common.ReadPaginationRequest
import com.khan366kos.chaosinversion.transport.models.common.ReadPaginationResponse
import com.khan366kos.chaosinversion.transport.models.project.CreateProjectRequest
import com.khan366kos.chaosinversion.transport.models.project.CreateProjectResponse
import com.khan366kos.chaosinversion.transport.models.project.ProjectTransport

class ProjectService(val repo: IProjectRepository) {
    suspend fun readProject(
        context: AppContext,
        request: ReadPaginationRequest
    ): ReadPaginationResponse<ProjectTransport> {
        context.setQuery(request)
        val result = repo.allWithPagination(DbProjectsRequest(
            Pagination(request.page ?: 0, request.size ?: 10)
        ))

        context.apply {
            projectsResponse = result.result
        }

        return context.toReadProjectsResponse()
    }

    suspend fun createProject(context: AppContext, request: CreateProjectRequest): CreateProjectResponse {
        context.setQuery(request)
        val result = repo.create(
            DbProjectRequest(
                project = request.createProject.toDomain()
            )
        )
        context.apply {
            projectResponse = result.result
        }

        return context.toCreateProjectResponse()
    }
}