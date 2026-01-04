package com.khan366kos.chaosinversion.project.service

import com.khan366kos.chaosinversion.domain.models.AppContext
import com.khan366kos.chaosinversion.domain.models.common.Pagination
import com.khan366kos.chaosinversion.domain.models.common.ResponseStatus
import com.khan366kos.chaosinversion.domain.models.project.repository.DbProjectIdRequest
import com.khan366kos.chaosinversion.domain.models.project.repository.DbProjectRequest
import com.khan366kos.chaosinversion.domain.models.project.repository.DbProjectsRequest
import com.khan366kos.chaosinversion.domain.models.project.repository.IProjectRepository
import com.khan366kos.chaosinversion.mappers.setQuery
import com.khan366kos.chaosinversion.mappers.toCreateProjectResponse
import com.khan366kos.chaosinversion.mappers.toDeleteProjectResponse
import com.khan366kos.chaosinversion.mappers.toReadProjectResponse
import com.khan366kos.chaosinversion.mappers.toReadProjectsResponse
import com.khan366kos.chaosinversion.mappers.toUpdateProjectResponse
import com.khan366kos.chaosinversion.transport.models.common.ReadPaginationRequest
import com.khan366kos.chaosinversion.transport.models.common.ReadPaginationResponse
import com.khan366kos.chaosinversion.transport.models.project.CreateProjectRequest
import com.khan366kos.chaosinversion.transport.models.project.CreateProjectResponse
import com.khan366kos.chaosinversion.transport.models.project.DeleteProjectRequest
import com.khan366kos.chaosinversion.transport.models.project.DeleteProjectResponse
import com.khan366kos.chaosinversion.transport.models.project.ProjectTransport
import com.khan366kos.chaosinversion.transport.models.project.ReadProjectRequest
import com.khan366kos.chaosinversion.transport.models.project.ReadProjectResponse
import com.khan366kos.chaosinversion.transport.models.project.UpdateProjectRequest
import com.khan366kos.chaosinversion.transport.models.project.UpdateProjectResponse

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
                project = context.createProject
            )
        )
        context.apply {
            projectResponse = result.result
        }

        return context.toCreateProjectResponse()
    }

    suspend fun readProject(context: AppContext, request: ReadProjectRequest): ReadProjectResponse {
        context.setQuery(request)
        val result = repo.read(DbProjectIdRequest(context.readProjectId))
        if (result.status == ResponseStatus.SUCCESS) {
            context.apply {
                projectResponse = result.result
            }
        } else {
            context.apply {
                errors.addAll(result.errors)
            }
        }

        return context.toReadProjectResponse()
    }

    suspend fun updateProject(context: AppContext, request: UpdateProjectRequest): UpdateProjectResponse {
        context.setQuery(request)
        val result = repo.update(
            DbProjectRequest(
                project = context.updateProject
            )
        )
        if (result.status == ResponseStatus.SUCCESS) {
            context.apply {
                projectResponse = result.result
            }
        } else {
            context.apply {
                errors.addAll(result.errors)
            }
        }

        return context.toUpdateProjectResponse()
    }

    suspend fun deleteProject(context: AppContext, request: DeleteProjectRequest): DeleteProjectResponse {
        context.setQuery(request)
        val result = repo.delete(DbProjectIdRequest(context.deleteProjectId))
        if (result.status == ResponseStatus.SUCCESS) {
            context.apply {
                projectResponse = result.result
            }
        } else {
            context.apply {
                errors.addAll(result.errors)
            }
        }

        return context.toDeleteProjectResponse()
    }
}