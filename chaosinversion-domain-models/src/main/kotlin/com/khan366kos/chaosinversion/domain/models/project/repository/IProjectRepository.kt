package com.khan366kos.chaosinversion.domain.models.project.repository

import com.khan366kos.chaosinversion.domain.models.common.Id
import com.khan366kos.chaosinversion.domain.models.common.Name
import com.khan366kos.chaosinversion.domain.models.project.Project

interface IProjectRepository {
    suspend fun create(request: DbProjectRequest): DbProjectResponse
    suspend fun read(request: DbProjectIdRequest): DbProjectResponse
    suspend fun findByName(name: Name): Project?
    suspend fun allWithPagination(request: DbProjectsRequest): DbProjectsResponse
    suspend fun update(project: Project): Project?
    suspend fun delete(id: Id): Boolean
    suspend fun size(): Int
}