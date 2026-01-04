package com.khan366kos.chaosinversion.ktor.app.helpers

import com.khan366kos.chaosinversion.domain.models.AppContext
import com.khan366kos.chaosinversion.transport.models.common.IBaseMessage
import com.khan366kos.chaosinversion.transport.models.common.RequestError
import com.khan366kos.chaosinversion.transport.models.project.CreateProjectRequest
import com.khan366kos.chaosinversion.transport.models.project.UpdateProjectRequest
import io.ktor.http.HttpMethod
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.httpMethod
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.put

suspend inline fun <reified T : IBaseMessage, reified U : IBaseMessage> ApplicationCall.handleRoute(
    crossinline block: suspend AppContext.(T) -> U,
) {
    val requestResult = recieveQuerySafe<T>()
    println("requestResult: $requestResult")
    val context = AppContext()
    requestResult.fold(
        onSuccess = { request ->
            val response = context.block(request)
            respond(response)
        },
        onFailure = { throwable ->
            respond(
                RequestError(
                    message = throwable.message ?: "Unknown error",
                )
            )
        }
    )
}

suspend inline fun <reified T> ApplicationCall.recieveQuerySafe(): Result<T> = runCatching {
    when (request.httpMethod) {
        HttpMethod.Post -> receive<CreateProjectRequest>()
        HttpMethod.Patch -> receive<UpdateProjectRequest>()
        HttpMethod.Get -> {
            if (request.queryParameters.entries().isNotEmpty()) {
                request.queryParameters.entries().takeIf { it.isNotEmpty() }?.let {
                    Json.decodeFromJsonElement<T>(buildJsonObject {
                        it.forEach { entry ->
                            when (val value = entry.value.firstOrNull()) {
                                null -> return@forEach
                                else -> put(entry.key, JsonPrimitive(value))
                            }
                        }
                    })
                }
            } else {
                Json.decodeFromJsonElement<T>(buildJsonObject { put("projectId", request.local.uri.split("/").last()) })
            }
        }

        else -> {}
    } as T
}