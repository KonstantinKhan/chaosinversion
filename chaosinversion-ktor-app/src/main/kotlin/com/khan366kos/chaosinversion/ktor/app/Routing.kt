package com.khan366kos.chaosinversion.ktor.app

import com.khan366kos.chaosinversion.ktor.app.controllers.readProject
import com.khan366kos.chaosinversion.project.service.ProjectService
import io.ktor.server.application.*
import io.ktor.server.routing.get
import io.ktor.server.routing.routing

fun Application.project(projectService: ProjectService) {
    routing {
        get("/project") {
            call.readProject(projectService)
        }
    }
}

