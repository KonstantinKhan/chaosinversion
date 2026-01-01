package com.khan366kos.chaosinversion.domain.models.common

data class Pagination(
    val page: Int = 0,
    val size: Int = 10,
) {
    val startIndex: Int by lazy {
        startIndex()
    }

    val endIndex: Int by lazy {
        endIndex()
    }

    private fun startIndex() = page * size
    private fun endIndex() = startIndex + size
}
