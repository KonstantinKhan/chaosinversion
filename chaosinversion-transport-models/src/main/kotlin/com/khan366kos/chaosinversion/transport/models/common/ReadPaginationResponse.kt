package com.khan366kos.chaosinversion.transport.models.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReadPaginationResponse<T>(
    @SerialName("messageType")
    override val messageType: String,
    @SerialName("requestId")
    override val requestId: String,
    @SerialName("result")
    override val result: ResultResponseTransport,
    @SerialName("errors")
    override val errors: List<RequestError>,
    @SerialName("data")
    val data: List<T>,
    @SerialName("page")
    val page: Int? = null,
    @SerialName("size")
    val size: Int? = null,
    @SerialName("totalElements")
    val totalElements: Int? = null,
    @SerialName("totalPages")
    val totalPages: Int? = null,
) : IBaseMessage, IBaseResponse