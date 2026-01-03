package com.khan366kos.chaosinversion.domain.models.project.repository

import com.khan366kos.chaosinversion.domain.models.common.Pagination

data class DbProjectsRequest(
    val pagination: Pagination = Pagination(),
)
