package com.khan366kos.chaosinversion.domain.models.common

@JvmInline
value class Id(private val uuid: String) {
    companion object {
        val NONE = Id("")
    }

    fun asString() = uuid
}