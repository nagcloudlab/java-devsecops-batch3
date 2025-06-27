package com.npci.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {

    @Bean
    public DataSource dataSource() {
        // library for creating a DataSource
        // For example, you can use HikariCP, Apache DBCP, or any other DataSource implementation
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5433/postgres");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres");
        // Additional properties can be set here if needed
        return dataSource;
    }

}
