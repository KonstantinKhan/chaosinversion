package com.khan366kos.chaosinversion.domain.models.common

import java.util.UUID

@JvmInline
value class Id(private val id: String) {
    constructor(uuid: UUID) : this(uuid.toString())
    companion object {
        val NONE = Id("")
    }

    fun asString() = id
    fun isNotEmpty() = id.isNotEmpty()
}