package com.khan366kos.chaosinversion.ktor.app

import com.khan366kos.chaosinversion.project.service.ProjectService
import io.ktor.server.application.*
import io.ktor.server.netty.EngineMain

fun main(args: Array<String>) {
    EngineMain.main(args)
}

fun Application.module(
    projectService: ProjectService = ProjectService(),
) {
    configureSerialization()
    configureHTTP()
    project(projectService)
}
