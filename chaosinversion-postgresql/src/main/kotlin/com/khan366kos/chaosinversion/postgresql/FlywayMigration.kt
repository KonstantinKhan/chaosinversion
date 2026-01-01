package com.khan366kos.chaosinversion.postgresql

import org.flywaydb.core.Flyway
import javax.sql.DataSource

class FlywayMigration {
    companion object {
        fun migrate(dataSource: DataSource) {
            val flyway = Flyway.configure()
                .dataSource(dataSource)
                .locations("classpath:db/migration")
                .load()

            flyway.migrate()
        }
    }
}