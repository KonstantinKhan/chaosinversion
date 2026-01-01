package com.khan366kos.chaosinversion.domain.models.db

import com.khan366kos.chaosinversion.domain.models.common.Pagination
import com.khan366kos.chaosinversion.domain.models.common.ResponseStatus
import com.khan366kos.chaosinversion.domain.models.project.Project

data class DbReadProjectResponse(
    val status: ResponseStatus = ResponseStatus.UNKNOWN,
    val result: List<Project> = emptyList(),
    val pagination: Pagination = Pagination(),
)
