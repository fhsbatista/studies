package com.example.kafka.config;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.Order;

import jakarta.annotation.PostConstruct;

@Configuration
@Order(1) // Executar antes de outras configurações
@ConditionalOnProperty(name = "spring.flyway.enabled", havingValue = "true", matchIfMissing = true)
public class FlywayInitializer implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(FlywayInitializer.class);

    @Autowired
    private DataSource dataSource;

    @Value("${spring.flyway.locations:classpath:db/migration}")
    private String[] locations;

    @Value("${spring.flyway.baseline-on-migrate:true}")
    private boolean baselineOnMigrate;

    @PostConstruct
    public void init() {
        logger.info("=== FlywayInitializer: Inicializando ===");
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        logger.info("=== Executando Flyway migrations ===");
        logger.info("DataSource: {}", dataSource);
        logger.info("Locations: {}", String.join(", ", locations));
        logger.info("Baseline on migrate: {}", baselineOnMigrate);
        
        try {
            Flyway flyway = Flyway.configure()
                    .dataSource(dataSource)
                    .locations(locations)
                    .baselineOnMigrate(baselineOnMigrate)
                    .validateOnMigrate(true)
                    .load();
            
            logger.info("Flyway configurado. Executando migrations...");
            int migrations = flyway.migrate().migrationsExecuted;
            logger.info("=== Flyway executado com sucesso! Migrations executadas: {} ===", migrations);
        } catch (Exception e) {
            logger.error("Erro ao executar Flyway migrations", e);
            throw new RuntimeException("Falha ao executar Flyway migrations", e);
        }
    }
}

