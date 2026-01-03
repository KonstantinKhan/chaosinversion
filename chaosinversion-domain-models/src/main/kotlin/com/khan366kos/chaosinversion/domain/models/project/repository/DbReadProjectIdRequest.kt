package com.khan366kos.chaosinversion.domain.models.project.repository

import com.khan366kos.chaosinversion.domain.models.common.Id

data class DbReadProjectIdRequest(
    val projectId: Id = Id.NONE,
)