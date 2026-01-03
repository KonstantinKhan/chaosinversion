package com.khan366kos.chaosinversion.mappers

import com.khan366kos.chaosinversion.domain.models.common.Id
import com.khan366kos.chaosinversion.domain.models.mock.Projects
import com.khan366kos.chaosinversion.domain.models.project.Project
import com.khan366kos.chaosinversion.transport.models.project.ProjectTransport
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import com.khan366kos.chaosinversion.domain.models.mock.Description as MockDescription

class ProjectMappersTest : ShouldSpec({

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
})
