package com.khan366kos.chaosinversion.domain.models.project.repository

import com.khan366kos.chaosinversion.domain.models.common.Id
import com.khan366kos.chaosinversion.domain.models.common.Name
import com.khan366kos.chaosinversion.domain.models.db.DbReadProjectRequest
import com.khan366kos.chaosinversion.domain.models.db.DbReadProjectResponse
import com.khan366kos.chaosinversion.domain.models.project.Project

interface ProjectRepository {
    suspend fun create(project: Project): Project
    suspend fun findById(id: Id): Project?
    suspend fun findByName(name: Name): Project?
    suspend fun findAll(request: DbReadProjectRequest): DbReadProjectResponse
    suspend fun update(project: Project): Project?
    suspend fun delete(id: Id): Boolean
}