package com.khan366kos.chaosinversion.domain.models

import com.khan366kos.chaosinversion.domain.models.common.Pagination
import com.khan366kos.chaosinversion.domain.models.project.Project

data class AppContext(
    var paginationRequest: Pagination = Pagination(),
    var projectsResponse: List<Project> = emptyList(),
)
