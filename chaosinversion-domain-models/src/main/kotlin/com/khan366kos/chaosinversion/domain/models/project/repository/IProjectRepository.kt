package com.khan366kos.chaosinversion.domain.models.project.repository

interface IProjectRepository {
    suspend fun create(request: DbProjectRequest): DbProjectResponse
    suspend fun read(request: DbProjectIdRequest): DbProjectResponse
    suspend fun allWithPagination(request: DbProjectsRequest): DbProjectsResponse
    suspend fun update(request: DbProjectRequest): DbProjectResponse
    suspend fun delete(request: DbProjectIdRequest): DbProjectResponse
    suspend fun size(): Int
}