package com.khan366kos.chaosinversion.domain.models

import com.khan366kos.chaosinversion.domain.models.common.Error
import com.khan366kos.chaosinversion.domain.models.common.Id
import com.khan366kos.chaosinversion.domain.models.common.Pagination
import com.khan366kos.chaosinversion.domain.models.project.Project
import java.util.Collections.emptyList

data class AppContext(
    var requestId: Id = Id.NONE,

    var createProject: Project = Project(),
    var readProjectId: Id = Id.NONE,
    var readProject: Project = Project(),
    var updateProject: Project = Project(),
    var deleteProjectId: Id = Id.NONE,

    val errors: MutableList<Error> = mutableListOf(),
    var paginationRequest: Pagination = Pagination(),

    var projectsResponse: List<Project> = emptyList(),
    var projectResponse: Project = Project(),
){
    fun addError(throwable: Throwable) {

    }
}
