package io.icodetech.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.*;
import javax.sql.DataSource;

@Startup
@Singleton
@TransactionManagement(value = TransactionManagementType.BEAN)
public class MigrationConfig {

    private final Logger log = LoggerFactory.getLogger(MigrationConfig.class);

    @Resource(lookup = "java:jboss/datasources/postgresDS")
    private DataSource dataSource;

    @PostConstruct
    private void onStartup() {

        log.info("Initializing Flyway migration");
        MigrationExecutor executor = MigrationExecutor.of(this.dataSource);
        executor.execute();
        log.info("Successfully to execute Flyway migration");

    }

}
