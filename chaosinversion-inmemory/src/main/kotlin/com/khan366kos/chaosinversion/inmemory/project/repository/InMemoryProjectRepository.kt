package com.khan366kos.chaosinversion.inmemory.project.repository

import com.khan366kos.chaosinversion.domain.models.common.Id
import com.khan366kos.chaosinversion.domain.models.common.Name
import com.khan366kos.chaosinversion.domain.models.common.Status
import com.khan366kos.chaosinversion.domain.models.db.DbReadProjectRequest
import com.khan366kos.chaosinversion.domain.models.db.DbReadProjectResponse
import com.khan366kos.chaosinversion.domain.models.project.Project
import com.khan366kos.chaosinversion.domain.models.project.repository.ProjectRepository
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.concurrent.ConcurrentHashMap

class InMemoryProjectRepository(
    val initProjects: List<Project> = emptyList(),
) : ProjectRepository {
    private val mutex = Mutex()
    private val projects = ConcurrentHashMap<Id, Project>()

    init {
        if (initProjects.isNotEmpty())
            initProjects.forEach {
                projects[it.id] = it
            }
    }

    override suspend fun create(project: Project): Project {
        return mutex.withLock {
            val newProject = if (project.id.asString().isEmpty()) {
                val newId = Id(generateId())
                project.copy(id = newId)
            } else {
                project
            }
            projects[newProject.id] = newProject
            newProject
        }
    }

    override suspend fun findById(id: Id): Project? {
        return mutex.withLock {
            projects[id]
        }
    }

    override suspend fun findByName(name: Name): Project? {
        return mutex.withLock {
            projects.values.find { it.name == name }
        }
    }

    override suspend fun findAll(request: DbReadProjectRequest): DbReadProjectResponse {
        return mutex.withLock {
            val pagination = request.pagination

            if (pagination.startIndex < 0 || pagination.size <= 0) {
                return@withLock DbReadProjectResponse(status = Status.SUCCESS, pagination = pagination)
            }

            projects.values.asSequence().sortedBy { it.name.asString() }
                .drop(pagination.startIndex)
                .take(pagination.size)
                .toList().let {
                    DbReadProjectResponse(
                        status = Status.SUCCESS,
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

    private fun generateId(): String {
        return "inmemory-${System.currentTimeMillis()}-${(Math.random() * 10_000).toInt()}"
    }
}