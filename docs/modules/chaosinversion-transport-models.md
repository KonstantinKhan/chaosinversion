# ChaosInversion transport models module

## Purpose

Describes transport models

## Location

[chaosinversion-transport-models](../../chaosinversion-transport-models)

## Directory Structure

```
src/main/kotlin/com/khan366kos/chaosinversion/transport/models/
├── common/
│   ├── BaseMessage.kt
│   ├── ReadPaginationRequest.kt
│   └── ReadPaginationResponse.kt
├── company/
│   └── Company.kt
├── description/
│   ├── DescriptionTransport.kt
│   └── ProjectStatusTransport.kt
├── finances/
│   └── Finances.kt
├── project/
│   └── ProjectTransport.kt
├── schedule/
│   └── Schedule.kt
├── structure/
│   └── Structure.kt
└── team/
    └── Team.kt
```

## Rules and Conventions

- All classes describing entities must have the suffix `Transport`
- All classes must be serializable
- All interfaces must start with `I`
- All nullable fields must have a default value of `null`
- All classes responsible for request must have the suffix `Request`
- All classes responsible for response must have the suffix `Response`
- All fields of serializable classes must be named using `@SerialName`, and the names must be written in `camelCase` style
- All `Request` classes must implement `IBaseMessage` and `IBaseRequest`
- All `Response` classes must implement `IBaseMessage` and `IBaseResponse`

## Examples

```kotlin
interface IBaseResponse {
    val requestId: String
    val result: ResultResponseTransport
    val errors: List<RequestError>
}
```

```kotlin
@Serializable
data class ProjectTransport(
    @SerialName("id")
    val id: String,
    @SerialName("description")
    val description: DescriptionTransport,
    @SerialName("teamId")
    val teamId: String? = null,
    @SerialName("companyId")
    val companyId: String? = null,
    @SerialName("scheduleId")
    val scheduleId: String? = null,
    @SerialName("financesId")
    val financesId: String? = null,
    @SerialName("structureId")
    val structureId: String? = null,
)
```
