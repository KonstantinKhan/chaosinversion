package com.khan366kos.chaosinversion.domain.models.project.repository

import com.khan366kos.chaosinversion.domain.models.common.Id

data class DbProjectIdRequest(
    val projectId: Id = Id.NONE,
)