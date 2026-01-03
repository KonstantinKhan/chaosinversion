package com.khan366kos.chaosinversion.mappers

import com.khan366kos.chaosinversion.domain.models.AppContext
import com.khan366kos.chaosinversion.domain.models.common.Error
import com.khan366kos.chaosinversion.domain.models.common.Id
import com.khan366kos.chaosinversion.domain.models.common.Pagination
import com.khan366kos.chaosinversion.domain.models.common.ProjectStatus
import com.khan366kos.chaosinversion.domain.models.mock.Projects
import com.khan366kos.chaosinversion.domain.models.project.Project
import com.khan366kos.chaosinversion.transport.models.common.ReadPaginationRequest
import com.khan366kos.chaosinversion.transport.models.common.ResultResponseTransport
import com.khan366kos.chaosinversion.transport.models.description.ProjectStatusTransport
import com.khan366kos.chaosinversion.transport.models.project.ProjectTransport
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import kotlin.math.ceil
import com.khan366kos.chaosinversion.domain.models.mock.Description as MockDescription

class MappersTest : ShouldSpec({

    context("map Project to transport model") {
        val project = Projects.TEST_PROJECTS.first()
        val transport = project.toTransport()

        should("map id correctly") {
            transport.id shouldBe project.id.asString()
        }

        should("map description correctly") {
            transport.description.id shouldBe project.description.id.asString()
            transport.description.title shouldBe project.description.title.value
            transport.description.status.toString() shouldBe project.description.status.toString()
        }

        should("not map name") {
            // ProjectTransport does not have a name field
        }

        should("have null for other fields") {
            transport.teamId shouldBe null
            transport.companyId shouldBe null
            transport.scheduleId shouldBe null
            transport.financesId shouldBe null
            transport.structureId shouldBe null
        }
    }

    context("map Description to transport model") {
        val description = MockDescription.TEST_DESCRIPTIONS.first()
        val transport = description.toTransport()

        should("map id correctly") {
            transport.id shouldBe description.id.asString()
        }

        should("map title correctly") {
            transport.title shouldBe description.title.value
        }

        should("map status correctly") {
            when (description.status) {
                ProjectStatus.ACTIVE -> transport.status shouldBe ProjectStatusTransport.ACTIVE
                ProjectStatus.PENDING -> transport.status shouldBe ProjectStatusTransport.PENDING
                ProjectStatus.UNKNOWN -> transport.status shouldBe ProjectStatusTransport.UNKNOWN
            }
        }
    }

    context("map project transport model to domain") {
        val transport = ProjectTransport(
            id = "123",
            description = MockDescription.TEST_DESCRIPTIONS.first().toTransport(),
        )
        val project = transport.toDomain()
        val defaultProject = Project()

        should("map id correctly") {
            project.id shouldBe Id(transport.id)
        }

        should("not map description and use default") {
            project.description shouldBe defaultProject.description
        }

        should("not map name and use default") {
            project.name shouldBe defaultProject.name
        }
    }

    context("map pagination request to domain") {
        val transport = ReadPaginationRequest(
            requestId = "123",
            page = 1,
            size = 10
        )
        val pagination = transport.toDomain()

        should("map page correctly") {
            pagination.page shouldBe transport.page
        }

        should("map size correctly") {
            pagination.size shouldBe transport.size
        }

        should("handle null page and size") {
            val transportWithNulls = ReadPaginationRequest(requestId = "123")
            val paginationWithNulls = transportWithNulls.toDomain()
            paginationWithNulls.page shouldBe 0
            paginationWithNulls.size shouldBe 10
        }
    }

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
        val response = context.toReadPaginationResponse()

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
        val response = context.toReadPaginationResponse()

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

    context("map Error to transport model") {
        val error = Error("Some error message", "some.field")
        val transport = error.toTransport()

        should("map message correctly") {
            transport.message shouldBe error.message
        }

        should("map field correctly") {
            transport.field shouldBe error.field
        }
    }
})
