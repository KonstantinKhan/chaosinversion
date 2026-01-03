package com.khan366kos.chaosinversion.domain.models

import com.khan366kos.chaosinversion.domain.models.common.Error
import com.khan366kos.chaosinversion.domain.models.common.Id
import com.khan366kos.chaosinversion.domain.models.common.Pagination
import com.khan366kos.chaosinversion.domain.models.project.Project

data class AppContext(
    var requestId: Id = Id.NONE,
    val errors: List<Error> = emptyList(),
    var paginationRequest: Pagination = Pagination(),
    var projectsResponse: List<Project> = emptyList(),
){
    fun addError(throwable: Throwable) {

    }
}
