package com.khan366kos.chaosinversion.domain.models.common

@JvmInline
value class Name(private val value: String) {
    companion object {
        val NONE = Name("")
    }
}