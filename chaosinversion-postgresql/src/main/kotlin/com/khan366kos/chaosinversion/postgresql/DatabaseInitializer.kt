package com.khan366kos.chaosinversion.postgresql

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.flywaydb.core.Flyway
import org.jetbrains.exposed.sql.Database
import javax.sql.DataSource

object DatabaseInitializer {
    fun init(
        jdbcUrl: String,
        driver: String,
        user: String,
        password: String,
        maxPoolSize: Int = 10
    ) {
        val dataSource = createHikariDataSource(jdbcUrl, driver, user, password, maxPoolSize)
        runMigrations(dataSource)
        Database.connect(dataSource)
    }

    private fun createHikariDataSource(
        jdbcUrl: String,
        driver: String,
        user: String,
        password: String,
        maxPoolSize: Int = 10
    ): HikariDataSource {
        val config = HikariConfig().apply {
            this.driverClassName = driver
            this.jdbcUrl = jdbcUrl
            this.username = user
            this.password = password
            this.maximumPoolSize = maxPoolSize

            this.connectionTimeout = 30000
            this.idleTimeout = 600000
            this.maxLifetime = 1800000
            this.minimumIdle = 5
            this.poolName = "chaosinversion-pool"
            this.isAutoCommit = true
        }

        return HikariDataSource(config)
    }

    private fun runMigrations(dataSource: DataSource) {
        val flyway = Flyway.configure()
            .dataSource(dataSource)
            .locations("classpath:db/migration")
            .load()

        flyway.migrate()
    }
}