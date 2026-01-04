package com.khan366kos.chaosinversion.domain.models

import com.khan366kos.chaosinversion.domain.models.common.Error
import com.khan366kos.chaosinversion.domain.models.common.Id
import com.khan366kos.chaosinversion.domain.models.common.Pagination
import com.khan366kos.chaosinversion.domain.models.project.Project

data class AppContext(
    var requestId: Id = Id.NONE,
    var requestProjectId: Id = Id.NONE,

    var readProject: Project = Project(),
    var createProject: Project = Project(),

    val errors: List<Error> = emptyList(),
    var paginationRequest: Pagination = Pagination(),

    var projectsResponse: List<Project> = emptyList(),
    var projectResponse: Project = Project(),
){
    fun addError(throwable: Throwable) {

    }
}
