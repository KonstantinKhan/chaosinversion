package com.khan366kos.chaosinversion.domain.models.db

import com.khan366kos.chaosinversion.domain.models.common.Pagination
import com.khan366kos.chaosinversion.domain.models.common.Status
import com.khan366kos.chaosinversion.domain.models.project.Project

data class DbReadProjectResponse(
    val status: Status = Status.UNKNOWN,
    val result: List<Project> = emptyList(),
    val pagination: Pagination = Pagination(),
)
