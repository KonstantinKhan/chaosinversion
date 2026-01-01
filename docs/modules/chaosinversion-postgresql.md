# ChaosInversion PostgreSQL module

## Location

[chaosinversion-postgresql](../../chaosinversion-postgresql)

## Description

The `chaosinversion-postgresql` module provides database access layer implementation using PostgreSQL as the primary data store. This module utilizes the Exposed ORM framework to handle database operations, providing a type-safe Kotlin API for database interactions.

## Purpose

This module is responsible for:
- Database schema management and migrations using Flyway
- Data access objects (DAOs) for domain entities
- Connection pooling configuration
- Transaction management
- Database-specific business logic implementation

## Dependencies

- `chaosinversion-domain-models`: For domain entity definitions
- Exposed ORM libraries for database operations
- PostgreSQL JDBC driver
- HikariCP for connection pooling
- Flyway for database migrations

## Key Components

- Database configuration and connection setup
- Data Access Objects (DAOs) extending Exposed's Entity and EntityClass
- Database transaction management utilities
- Schema definition and migration tools using Flyway
- Migration files in `src/main/resources/db/migration/`

