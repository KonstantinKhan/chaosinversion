package com.khan366kos.chaosinversion.domain.models.common

import java.util.UUID

@JvmInline
value class Id(private val uuid: String) {
    constructor(uuid: UUID) : this(uuid.toString())
    companion object {
        val NONE = Id("")
    }

    fun asString() = uuid
    fun isNotEmpty() = uuid.isNotEmpty()
}