package com.khan366kos.chaosinversion.project.service

import com.khan366kos.chaosinversion.domain.models.AppContext
import com.khan366kos.chaosinversion.domain.models.common.Id
import com.khan366kos.chaosinversion.domain.models.common.Name
import com.khan366kos.chaosinversion.domain.models.project.Project
import com.khan366kos.chaosinversion.mappers.setQuery
import com.khan366kos.chaosinversion.mappers.toReadPaginationResponse
import com.khan366kos.chaosinversion.transport.models.common.ReadPaginationRequest
import com.khan366kos.chaosinversion.transport.models.common.ReadPaginationResponse
import com.khan366kos.chaosinversion.transport.models.project.ProjectTransport

class ProjectService {
    suspend fun readProject(
        context: AppContext,
        request: ReadPaginationRequest
    ): ReadPaginationResponse<ProjectTransport> {
        context.setQuery(request)

        // Generate mock projects for demonstration
        val allProjects = (1..100).map {
            Project(
                id = Id("project-$it"), name = Name("Project $it")
            )
        }

        with(context.paginationRequest) {
            val paginatedProjects: List<Project> = if (startIndex < allProjects.size) {
                allProjects.subList(startIndex, minOf(endIndex, allProjects.size))
            } else {
                emptyList()
            }

            context.apply {
                projectsResponse = paginatedProjects
            }
        }

        return context.toReadPaginationResponse()
    }
}