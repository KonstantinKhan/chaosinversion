package com.khan366kos.chaosinversion.mappers

import com.khan366kos.chaosinversion.domain.models.common.Error
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class ErrorMapperTest : ShouldSpec({
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
