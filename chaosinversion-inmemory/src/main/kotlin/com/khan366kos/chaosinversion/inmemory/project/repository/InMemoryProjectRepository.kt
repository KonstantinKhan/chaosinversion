package com.khan366kos.chaosinversion.inmemory.project.repository

import com.khan366kos.chaosinversion.domain.models.common.Error
import com.khan366kos.chaosinversion.domain.models.common.Id
import com.khan366kos.chaosinversion.domain.models.common.Name
import com.khan366kos.chaosinversion.domain.models.common.ResponseStatus
import com.khan366kos.chaosinversion.domain.models.project.repository.DbProjectsRequest
import com.khan366kos.chaosinversion.domain.models.project.repository.DbProjectsResponse
import com.khan366kos.chaosinversion.domain.models.project.Project
import com.khan366kos.chaosinversion.domain.models.project.repository.DbProjectIdRequest
import com.khan366kos.chaosinversion.domain.models.project.repository.DbProjectRequest
import com.khan366kos.chaosinversion.domain.models.project.repository.DbProjectResponse
import com.khan366kos.chaosinversion.domain.models.project.repository.IProjectRepository
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.concurrent.ConcurrentHashMap

class InMemoryProjectRepository(
    initProjects: List<Project> = emptyList(),
) : IProjectRepository {
    private val mutex = Mutex()
    private val projects = ConcurrentHashMap<Id, Project>()

    init {
        if (initProjects.isNotEmpty())
            initProjects.forEach {
                projects[it.id] = it
            }
    }

    override suspend fun create(request: DbProjectRequest): DbProjectResponse {
        return mutex.withLock {
            val newProject = if (request.project.id == Id.NONE) {
                val newId = Id(generateId())
                request.project.copy(id = newId)
            } else {
                request.project
            }
            projects[newProject.id] = newProject
            DbProjectResponse(
                status = ResponseStatus.SUCCESS,
                result = newProject
            )
        }
    }

    override suspend fun read(request: DbProjectIdRequest): DbProjectResponse {

        val result = mutex.withLock {
            projects[request.projectId] ?: Project()
        }
        return if (result != Project())
            DbProjectResponse(
                status = ResponseStatus.SUCCESS,
                result = result,
            )
        else DbProjectResponse(
            status = ResponseStatus.FAILURE,
            errors = listOf(
                Error(message = "Not found project with id: ${request.projectId}", field = "projectId"),
            )
        )
    }

    override suspend fun findByName(name: Name): Project? {
        return mutex.withLock {
            projects.values.find { it.name == name }
        }
    }

    override suspend fun allWithPagination(request: DbProjectsRequest): DbProjectsResponse {
        return mutex.withLock {
            val pagination = request.pagination

            if (pagination.startIndex < 0 || pagination.size <= 0) {
                return@withLock DbProjectsResponse(status = ResponseStatus.SUCCESS, pagination = pagination)
            }

            projects.values.asSequence().sortedBy { it.name.asString() }
                .drop(pagination.startIndex)
                .take(pagination.size)
                .toList().let {
                    DbProjectsResponse(
                        status = ResponseStatus.SUCCESS,
                        result = it,
                        pagination = pagination
                    )
                }
        }
    }

    override suspend fun update(project: Project): Project? {
        return mutex.withLock {
            if (projects.containsKey(project.id)) {
                projects[project.id] = project
                project
            } else {
                null
            }
        }
    }

    override suspend fun delete(id: Id): Boolean {
        return mutex.withLock {
            projects.remove(id) != null
        }
    }

    override suspend fun size(): Int = projects.size

    private fun generateId(): String {
        return "inmemory-${System.currentTimeMillis()}-${(Math.random() * 10_000).toInt()}"
    }
}