package com.khan366kos.chaosinversion.ktor.app

import com.khan366kos.chaosinversion.domain.models.mock.Projects
import com.khan366kos.chaosinversion.domain.models.project.repository.IProjectRepository
import com.khan366kos.chaosinversion.inmemory.project.repository.InMemoryProjectRepository
import com.khan366kos.chaosinversion.project.service.ProjectService
import io.ktor.server.application.*
import io.ktor.server.netty.EngineMain

fun main(args: Array<String>) {
    EngineMain.main(args)
}

fun Application.module(
    repo: IProjectRepository = InMemoryProjectRepository(Projects.TEST_PROJECTS),
    projectService: ProjectService = ProjectService(repo)
) {
    configureSerialization()
    configureHTTP()
    project(projectService)
}
