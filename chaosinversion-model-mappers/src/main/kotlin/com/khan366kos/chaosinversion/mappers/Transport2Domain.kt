package com.khan366kos.chaosinversion.mappers

import com.khan366kos.chaosinversion.domain.models.AppContext
import com.khan366kos.chaosinversion.domain.models.common.Id
import com.khan366kos.chaosinversion.domain.models.common.Pagination
import com.khan366kos.chaosinversion.domain.models.project.Project
import com.khan366kos.chaosinversion.transport.models.common.ReadPaginationRequest
import com.khan366kos.chaosinversion.transport.models.project.ProjectTransport

fun AppContext.setQuery(request: ReadPaginationRequest) = apply {
    paginationRequest = request.toDomain()
}

fun ProjectTransport.toDomain(): Project = Project(
    id = Id(id)
)

fun ReadPaginationRequest.toDomain(): Pagination = Pagination(
    page = page ?: 0,
    size = size ?: 10,

)