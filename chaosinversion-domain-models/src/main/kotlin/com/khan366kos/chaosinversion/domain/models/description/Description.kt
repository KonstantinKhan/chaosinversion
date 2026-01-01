package com.khan366kos.chaosinversion.domain.models.description

import com.khan366kos.chaosinversion.domain.models.common.Id
import com.khan366kos.chaosinversion.domain.models.common.ProjectStatus
import com.khan366kos.chaosinversion.domain.models.common.Title

data class Description(
    val id: Id = Id.NONE,
    val status: ProjectStatus = ProjectStatus.UNKNOWN,
    val title: Title = Title.NONE,
)
