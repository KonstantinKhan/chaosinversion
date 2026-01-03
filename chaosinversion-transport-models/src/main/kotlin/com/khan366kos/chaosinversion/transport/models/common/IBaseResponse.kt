package com.khan366kos.chaosinversion.transport.models.common

interface IBaseResponse {
    val requestId: String
    val result: ResultResponseTransport
    val errors: List<RequestError>
}