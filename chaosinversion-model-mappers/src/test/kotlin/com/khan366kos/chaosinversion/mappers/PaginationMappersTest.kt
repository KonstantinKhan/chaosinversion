package com.khan366kos.chaosinversion.mappers

import com.khan366kos.chaosinversion.transport.models.common.ReadPaginationRequest
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class PaginationMappersTest : ShouldSpec({

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
})
