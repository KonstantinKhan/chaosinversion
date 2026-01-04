package com.khan366kos.chaosinversion.domain.models.mock

import com.khan366kos.chaosinversion.domain.models.common.Id
import com.khan366kos.chaosinversion.domain.models.common.Title
import com.khan366kos.chaosinversion.domain.models.description.Description

object Description {
    val TEST_DESCRIPTIONS = List(5) { index ->
        Description(
            id = Id(UUIDs.TEST_UUIDS[index]),
            title = Title("Description $index"),
        )
    }
}