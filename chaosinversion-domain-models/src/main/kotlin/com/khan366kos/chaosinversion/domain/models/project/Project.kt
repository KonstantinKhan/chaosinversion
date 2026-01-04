package com.khan366kos.chaosinversion.domain.models.project

import com.khan366kos.chaosinversion.domain.models.common.Id
import com.khan366kos.chaosinversion.domain.models.description.Description

data class Project(
    val id: Id = Id.NONE,
    val description: Description = Description(),
)
