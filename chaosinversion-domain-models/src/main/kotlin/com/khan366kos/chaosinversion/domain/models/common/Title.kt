package com.khan366kos.chaosinversion.domain.models.common

@JvmInline
value class Title(val value: String) {
    companion object {
        val NONE = Title("")
    }
    fun asString() = value
}