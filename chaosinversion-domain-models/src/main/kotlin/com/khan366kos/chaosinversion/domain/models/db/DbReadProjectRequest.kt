package com.khan366kos.chaosinversion.domain.models.db

import com.khan366kos.chaosinversion.domain.models.common.Pagination

data class DbReadProjectRequest(
    val pagination: Pagination = Pagination(),
)
