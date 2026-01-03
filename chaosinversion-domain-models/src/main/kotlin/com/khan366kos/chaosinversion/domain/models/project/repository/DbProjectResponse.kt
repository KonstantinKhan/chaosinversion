package com.khan366kos.chaosinversion.domain.models.project.repository

import com.khan366kos.chaosinversion.domain.models.common.Error
import com.khan366kos.chaosinversion.domain.models.common.IDbResponse
import com.khan366kos.chaosinversion.domain.models.common.ResponseStatus
import com.khan366kos.chaosinversion.domain.models.project.Project

data class DbProjectResponse(
    override val status: ResponseStatus,
    override val errors: List<Error> = emptyList(),
    override val result: Project = Project(),
) : IDbResponse<Project> {
}