# ChaosInversion Project

## Rules and Conventions

- Before completing the task, be sure to first read the documentation on the relevant module, if any
- Uses Gradle Version Catalogs for dependency management in `libs.versions.toml`

## Overview

ChaosInversion is a Ktor-based web application built using Kotlin. It's a multi-module Gradle project.

## Architecture

The project consists of the following modules:

| module                            | description                                                                                              | docs                                                                                    |
|-----------------------------------|----------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------|
| `chaosinversion-ktor-app`         | The main Ktor server app                                                                                 | [chaosinversion-ktor-app](./docs/modules/chaosinversion-ktor-app.md)                    |
| `chaosinversion-transport-models` | The common module containing data models and serialization definitions                                   | [chaosinversion-transport-models.md](./docs/modules/chaosinversion-transport-models.md) |
| `chaosinversion-domain-models`    | The module containing core business entities and domain models                                           | [chaosinversion-domain-models.md](./docs/modules/chaosinversion-domain-models.md)       |
| `chaosinversion-model-mappers`    | The module providing bidirectional mapping between transport and domain models                           | [chaosinversion-model-mappers.md](./docs/modules/chaosinversion-model-mappers.md)       |
| `chaosinversion-project-service`  | The module containing business logic and service layer implementations for project-related functionality | [chaosinversion-project-service.md](./docs/modules/chaosinversion-project-service.md)   |
| `chaosinversion-postgresql`       | The module containing database access layer implementation using PostgreSQL                              | [chaosinversion-postgresql](./docs/modules/chaosinversion-postgresql.md)                |

## Technologies Used

- **Kotlin**: Primary programming language
- **Ktor**: Web framework for building the server application
- **Gradle**: Build system with Kotlin DSL
- **kotlinx.serialization**: JSON serialization library
- **Netty**: Server engine for Ktor
- **Logback**: Logging framework
- **CORS**: Cross-Origin Resource Sharing support

## Features

- Content Negotiation: Automatic content conversion based on Content-Type and Accept headers
- Routing: Structured routing DSL for defining endpoints
- JSON Serialization: Using kotlinx.serialization library
- CORS Support: Cross-Origin Resource Sharing configuration

## Building and Running

### Prerequisites

- Java 21 or higher
- Gradle (wrapper included in project)

### Build Commands

| Command           | Description      |
|-------------------|------------------|
| `./gradlew test`  | Run the tests    |
| `./gradlew build` | Build everything |
| `./gradlew run`   | Run the server   |

## Configuration

### Application Configuration

The `application.conf` file contains:

- Port configuration (default: 8080, can be overridden with PORT environment variable)
- Module configuration pointing to the main application module
