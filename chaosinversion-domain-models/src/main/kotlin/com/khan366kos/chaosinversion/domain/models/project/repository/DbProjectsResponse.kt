package com.khan366kos.chaosinversion.domain.models.project.repository

import com.khan366kos.chaosinversion.domain.models.common.Pagination
import com.khan366kos.chaosinversion.domain.models.common.ResponseStatus
import com.khan366kos.chaosinversion.domain.models.project.Project

data class DbProjectsResponse(
    val status: ResponseStatus = ResponseStatus.UNKNOWN,
    val result: List<Project> = emptyList(),
    val pagination: Pagination = Pagination(),
)
