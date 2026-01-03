package com.khan366kos.chaosinversion.mappers

import com.khan366kos.chaosinversion.domain.models.common.ProjectStatus
import com.khan366kos.chaosinversion.transport.models.description.ProjectStatusTransport
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import com.khan366kos.chaosinversion.domain.models.mock.Description as MockDescription

class DescriptionMappersTest : ShouldSpec({

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
})
