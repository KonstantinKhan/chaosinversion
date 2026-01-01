package com.khan366kos.chaosinversion.ktor.app.controllers

import com.khan366kos.chaosinversion.ktor.app.helpers.handleRoute
import com.khan366kos.chaosinversion.project.service.ProjectService
import com.khan366kos.chaosinversion.transport.models.common.ReadPaginationRequest
import com.khan366kos.chaosinversion.transport.models.common.ReadPaginationResponse
import com.khan366kos.chaosinversion.transport.models.project.ProjectTransport
import io.ktor.server.application.ApplicationCall

suspend fun ApplicationCall.readProject(projectService: ProjectService) {
    handleRoute<ReadPaginationRequest, ReadPaginationResponse<ProjectTransport>> { request ->
        projectService.readProject(this, request)
    }
}