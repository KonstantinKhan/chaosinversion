package com.khan366kos.chaosinversion.domain.models.project.repository

import com.khan366kos.chaosinversion.domain.models.common.Pagination

data class DbReadProjectsRequest(
    val pagination: Pagination = Pagination(),
)
