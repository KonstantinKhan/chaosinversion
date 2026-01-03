package com.khan366kos.chaosinversion.domain.models.common

interface IDbResponse<T> {
    val status: ResponseStatus
    val errors: List<Error>
    val result: T
}