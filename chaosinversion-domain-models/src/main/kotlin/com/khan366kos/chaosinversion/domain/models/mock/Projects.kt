package com.khan366kos.chaosinversion.domain.models.mock

import com.khan366kos.chaosinversion.domain.models.project.Project
import com.khan366kos.chaosinversion.domain.models.common.Id
import com.khan366kos.chaosinversion.domain.models.common.Name
import java.util.UUID

object Projects {
    val TEST_PROJECTS = List(20) { index ->
        Project(
            id = Id(UUID.randomUUID().toString()),
            name = Name("Project ${index + 1}"),
            description = Description.TEST_DESCRIPTIONS[index],
        )
    }
}