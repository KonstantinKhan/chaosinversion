package com.khan366kos.chaosinversion.inmemory.project.repository

import com.khan366kos.chaosinversion.domain.models.common.Id
import com.khan366kos.chaosinversion.domain.models.common.Name
import com.khan366kos.chaosinversion.domain.models.common.Pagination
import com.khan366kos.chaosinversion.domain.models.project.repository.DbReadProjectsRequest
import com.khan366kos.chaosinversion.domain.models.mock.Projects
import com.khan366kos.chaosinversion.domain.models.project.Project
import com.khan366kos.chaosinversion.domain.models.project.repository.DbReadProjectIdRequest
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import kotlinx.coroutines.test.runTest

class InMemoryProjectRepositoryTest : ShouldSpec({
    context("InMemoryProjectRepository") {
        val repository = InMemoryProjectRepository(Projects.TEST_PROJECTS)

        should("create a project with generated ID when ID is empty") {
            runTest {
                val project = Project(name = Name("Test Project"))

                val result = repository.create(project)

                result.id.shouldNotBeNull()
                result.name.asString() shouldBe "Test Project"
                result.id.asString().startsWith("inmemory-") shouldBe true
            }
        }

        should("create a project with provided ID when ID is not empty") {
            runTest {
                val projectId = Id("custom-id")
                val project = Project(id = projectId, name = Name("Test Project"))

                val result = repository.create(project)

                result.id shouldBe projectId
                result.name.asString() shouldBe "Test Project"
            }
        }

        should("find a project by ID") {
            runTest {
                val project = Project(name = Name("Test Project"))
                val createdProject = repository.create(project)

                val foundProject = repository.project(DbReadProjectIdRequest(createdProject.id))

                foundProject.shouldNotBeNull()
                foundProject.result.id shouldBe createdProject.id
                foundProject.result.name shouldBe createdProject.name
            }
        }

        should("return null when project is not found by ID") {
            runTest {
                val foundProject = repository.project(DbReadProjectIdRequest(Id("non-existent-id")))
                foundProject.result shouldBe Project()
                foundProject.errors.size shouldNotBe 0
            }
        }

        should("find a project by name") {
            runTest {
                val project = Project(name = Name("Unique Project Name"))
                repository.create(project)

                val foundProject = repository.findByName(Name("Unique Project Name"))

                foundProject.shouldNotBeNull()
                foundProject.name.asString() shouldBe "Unique Project Name"
            }
        }

        should("return null when project is not found by name") {
            runTest {
                val foundProject = repository.findByName(Name("Non-existent Name"))

                foundProject shouldBe null
            }
        }

        should("find all projects with default pagination") {
            runTest {
                val response = repository.projects(DbReadProjectsRequest())
                response.result.size shouldBe response.pagination.size
            }
        }

        should("find all projects with custom pagination") {
            runTest {
                val response = repository.projects(
                    DbReadProjectsRequest(
                        pagination = Pagination(page = 1, size = 20),
                    )
                )
                val size = repository.size()
                if (response.pagination.startIndex > size) response.result.size shouldBe 0
                else
                    if (response.pagination.endIndex > size) {
                        val paginationSize = size -
                                (response.pagination.size * (response.pagination.page + 1) - response.pagination.size)
                        response.result.size shouldBe paginationSize
                    } else {
                        response.result.size shouldBe response.pagination.size
                    }
            }
        }

        should("find all projects with custom pagination and smaller size") {
            runTest {
                val response = repository.projects(
                    DbReadProjectsRequest(
                        pagination = Pagination(page = 6, size = 16),
                    )
                )
                val size = repository.size()
                if (response.pagination.startIndex > size) response.result.size shouldBe 0
                else
                    if (response.pagination.endIndex > size) {
                        val paginationSize = size -
                                (response.pagination.size * (response.pagination.page + 1) - response.pagination.size)
                        response.result.size shouldBe paginationSize
                    } else {
                        response.result.size shouldBe response.pagination.size
                    }
            }
        }

        should("update an existing project") {
            runTest {
                val project = Project(name = Name("Original Name"))
                val createdProject = repository.create(project)

                val updatedProject = createdProject.copy(name = Name("Updated Name"))
                val result = repository.update(updatedProject)

                result shouldBe updatedProject
                repository.project(DbReadProjectIdRequest(createdProject.id)).result.name.asString() shouldBe "Updated Name"
            }
        }

        should("return null when updating a non-existent project") {
            runTest {
                val nonExistentProject = Project(id = Id("non-existent"), name = Name("Some Name"))
                val result = repository.update(nonExistentProject)

                result shouldBe null
            }
        }

        should("delete an existing project") {
            runTest {
                val project = Project(name = Name("Project to Delete"))
                val createdProject = repository.create(project)
                val result = repository.delete(createdProject.id)

                result shouldBe true
                repository.project(DbReadProjectIdRequest(createdProject.id)).result shouldBe Project()
            }
        }

        should("return false when deleting a non-existent project") {
            runTest {
                val result = repository.delete(Id("non-existent-id"))

                result shouldBe false
            }
        }
    }
})