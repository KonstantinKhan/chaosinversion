package com.khan366kos.chaosinversion.domain.models.mock

import java.util.UUID

object UUIDs {
    val TEST_UUIDS = List(20) { _ ->
        UUID.randomUUID().toString()
    }
}