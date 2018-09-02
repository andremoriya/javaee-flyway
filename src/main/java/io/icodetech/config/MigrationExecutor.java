package io.icodetech.config;

import io.icodetech.exception.MigrationException;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.MigrationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.util.Objects;

public class MigrationExecutor {

    private final Logger log = LoggerFactory.getLogger(MigrationExecutor.class);

    private Flyway flyway;
    private final DataSource dataSource;

    private MigrationExecutor(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static MigrationExecutor of(DataSource dataSource) {
        return new MigrationExecutor(dataSource);
    }

    public void execute() {
        if(Objects.isNull(dataSource)) {
            log.error("DataSource instance is null!");
            throw new MigrationException("DataSource not found to execute the database migration!");
        }

        this.flyway = new Flyway();
        this.flyway.setDataSource(dataSource);
        this.flyway.setLocations("classpath:db/migration");

        MigrationInfo info = this.flyway.info().current();
        if (Objects.isNull(info)) {
            log.info("Database not found to the actual datasource");
        }
        else {
            log.info("Database with the version: {}: {}", info.getVersion(), info.getDescription());
            log.info("Successfully migrated to database version: {}", info.getVersion());
        }

        this.flyway.migrate();
    }

}
