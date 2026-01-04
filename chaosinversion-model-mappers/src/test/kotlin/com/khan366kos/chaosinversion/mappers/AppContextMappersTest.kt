package com.khan366kos.chaosinversion.mappers

import com.khan366kos.chaosinversion.domain.models.AppContext
import com.khan366kos.chaosinversion.domain.models.common.Error
import com.khan366kos.chaosinversion.domain.models.common.Id
import com.khan366kos.chaosinversion.domain.models.common.Pagination
import com.khan366kos.chaosinversion.domain.models.mock.Projects
import com.khan366kos.chaosinversion.transport.models.common.ReadPaginationRequest
import com.khan366kos.chaosinversion.transport.models.common.ResultResponseTransport
import com.khan366kos.chaosinversion.transport.models.project.ReadProjectRequest
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import kotlin.math.ceil

class AppContextMappersTest : ShouldSpec({

    context("set query from request") {
        val context = AppContext()
        val requestId = "123"
        val request = ReadPaginationRequest(
            requestId = requestId,
            page = 2,
            size = 5
        )
        context.setQuery(request)

        should("set request id correctly") {
            context.requestId shouldBe Id(requestId)
        }

        should("set page correctly") {
            context.paginationRequest.page shouldBe request.page
        }

        should("set size correctly") {
            context.paginationRequest.size shouldBe request.size
        }

        should("generate request id if not provided") {
            val context2 = AppContext()
            val request2 = ReadPaginationRequest(page = 1, size = 5)
            context2.setQuery(request2)
            context2.requestId shouldNotBe Id.NONE
        }
    }

    context("map app context to read pagination response") {
        val context = AppContext(
            requestId = Id("req-123"),
            paginationRequest = Pagination(page = 1, size = 10),
            projectsResponse = Projects.TEST_PROJECTS
        )
        val response = context.toReadProjectsResponse()

        should("map request id correctly") {
            response.requestId shouldBe context.requestId.asString()
        }

        should("map result to success when no errors") {
            response.result shouldBe ResultResponseTransport.SUCCESS
            response.errors.isEmpty() shouldBe true
        }

        should("map page correctly") {
            response.page shouldBe context.paginationRequest.page
        }

        should("map size correctly") {
            response.size shouldBe context.paginationRequest.size
        }

        should("map totalElements correctly") {
            response.totalElements shouldBe context.projectsResponse.size
        }

        should("map totalPages correctly") {
            response.totalPages shouldBe ceil(
                context.projectsResponse.size.toDouble() / context.paginationRequest.size
            ).toInt()
        }

        should("map data size correctly") {
            response.data.size shouldBe context.projectsResponse.size
        }

        should("map data content correctly") {
            response.data.first().id shouldBe context.projectsResponse.first().id.asString()
        }
    }

    context("map app context to read pagination response with errors") {
        val error = Error("Test error", "test.field")
        val context = AppContext(
            requestId = Id("req-456"),
            errors = mutableListOf(error)
        )
        val response = context.toReadProjectsResponse()

        should("map request id correctly") {
            response.requestId shouldBe context.requestId.asString()
        }

        should("map result to error when errors are present") {
            response.result shouldBe ResultResponseTransport.ERROR
        }

        should("map errors correctly") {
            response.errors.size shouldBe 1
            response.errors.first().message shouldBe error.message
            response.errors.first().field shouldBe error.field
        }

        should("have empty data") {
            response.data.isEmpty() shouldBe true
        }
    }

    context("set query from ReadProjectRequest") {
        val context = AppContext()
        val requestId = "123"
        val projectId = "456"
        val request = ReadProjectRequest(
            requestId = requestId,
            projectId = projectId
        )
        context.setQuery(request)

        should("set request id correctly") {
            context.requestId shouldBe Id(requestId)
        }

        should("set project id correctly") {
            context.readProjectId shouldBe Id(projectId)
        }

        should("generate request id if not provided") {
            val context2 = AppContext()
            val request2 = ReadProjectRequest(projectId = "789")
            context2.setQuery(request2)
            context2.requestId shouldNotBe Id.NONE
        }

        should("use Id.NONE for project id if not provided") {
            val context3 = AppContext()
            val request3 = ReadProjectRequest(requestId = "999")
            context3.setQuery(request3)
            context3.readProjectId shouldBe Id.NONE
        }
    }

    context("map app context to read project response without errors") {
        val project = Projects.TEST_PROJECTS.first()
        val context = AppContext(
            requestId = Id("req-123"),
            readProject = project
        )
        val response = context.toReadProjectResponse()

        should("map request id correctly") {
            response.requestId shouldBe context.requestId.asString()
        }

        should("map result to success when no errors") {
            response.result shouldBe ResultResponseTransport.SUCCESS
            response.errors.isEmpty() shouldBe true
        }

        should("map read project correctly") {
            response.readProject.id shouldBe context.readProject.id.asString()
            response.readProject.description.id shouldBe context.readProject.description.id.asString()
            response.readProject.description.title shouldBe context.readProject.description.title.value
        }

        should("have correct message type") {
            response.messageType shouldBe "ReadProjectResponse"
        }
    }

    context("map app context to read project response with errors") {
        val error = Error("Test error", "test.field")
        val context = AppContext(
            requestId = Id("req-456"),
            errors = mutableListOf(error)
        )
        val response = context.toReadProjectResponse()

        should("map request id correctly") {
            response.requestId shouldBe context.requestId.asString()
        }

        should("map result to error when errors are present") {
            response.result shouldBe ResultResponseTransport.ERROR
        }

        should("map errors correctly") {
            response.errors.size shouldBe 1
            response.errors.first().message shouldBe error.message
            response.errors.first().field shouldBe error.field
        }

        should("still map the read project even when errors exist") {
            response.readProject.id shouldBe context.readProject.id.asString()
        }
    }
})
