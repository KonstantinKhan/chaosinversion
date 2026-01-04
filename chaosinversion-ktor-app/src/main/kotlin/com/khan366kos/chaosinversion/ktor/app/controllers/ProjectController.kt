package com.khan366kos.chaosinversion.ktor.app.controllers

import com.khan366kos.chaosinversion.ktor.app.helpers.handleRoute
import com.khan366kos.chaosinversion.project.service.ProjectService
import com.khan366kos.chaosinversion.transport.models.common.ReadPaginationRequest
import com.khan366kos.chaosinversion.transport.models.common.ReadPaginationResponse
import com.khan366kos.chaosinversion.transport.models.project.CreateProjectRequest
import com.khan366kos.chaosinversion.transport.models.project.CreateProjectResponse
import com.khan366kos.chaosinversion.transport.models.project.ProjectTransport
import com.khan366kos.chaosinversion.transport.models.project.ReadProjectRequest
import com.khan366kos.chaosinversion.transport.models.project.ReadProjectResponse
import com.khan366kos.chaosinversion.transport.models.project.UpdateProjectRequest
import com.khan366kos.chaosinversion.transport.models.project.UpdateProjectResponse
import io.ktor.server.application.ApplicationCall

suspend fun ApplicationCall.findAll(projectService: ProjectService) {
    handleRoute<ReadPaginationRequest, ReadPaginationResponse<ProjectTransport>> { request ->
        projectService.readProject(this, request)
    }
}

suspend fun ApplicationCall.createProject(projectService: ProjectService) {
    handleRoute<CreateProjectRequest, CreateProjectResponse> { request ->
        projectService.createProject(this, request)
    }
}

suspend fun ApplicationCall.readProject(projectService: ProjectService) {
    handleRoute<ReadProjectRequest, ReadProjectResponse> { request ->
        projectService.readProject(this, request)
    }
}

suspend fun ApplicationCall.updateProject(projectService: ProjectService) {
    handleRoute<UpdateProjectRequest, UpdateProjectResponse> { request ->
        projectService.updateProject(this, request)
    }
}