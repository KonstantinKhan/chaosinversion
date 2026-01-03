package com.khan366kos.chaosinversion.domain.models.project.repository

import com.khan366kos.chaosinversion.domain.models.common.Id
import com.khan366kos.chaosinversion.domain.models.common.Name
import com.khan366kos.chaosinversion.domain.models.project.Project

interface IProjectRepository {
    suspend fun create(project: Project): Project
    suspend fun project(request: DbReadProjectIdRequest): DbReadProjectResponse
    suspend fun findByName(name: Name): Project?
    suspend fun projects(request: DbReadProjectsRequest): DbReadProjectsResponse
    suspend fun update(project: Project): Project?
    suspend fun delete(id: Id): Boolean
    suspend fun size(): Int
}