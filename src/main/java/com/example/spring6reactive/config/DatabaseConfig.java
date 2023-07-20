package com.example.spring6reactive.config;

import io.r2dbc.spi.Connection;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.DatabasePopulator;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;
import org.springframework.r2dbc.connection.init.ScriptException;
import reactor.core.publisher.Mono;

@Configuration
@EnableR2dbcAuditing
public class DatabaseConfig {

    @Value("classpath:/schema.sql")
    Resource resource;

    @Bean
    ConnectionFactoryInitializer initializer(ConnectionFactory factory) {
        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(factory);
        initializer.setDatabasePopulator(new ResourceDatabasePopulator(resource));
        return initializer;
    }
}
