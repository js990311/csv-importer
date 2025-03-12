package com.csv.importer.yaml.service;

import com.csv.importer.yaml.dto.Database;
import com.csv.importer.yaml.dto.Root;
import com.csv.importer.yaml.dto.Work;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class ServiceManager {
    private final ImportQueryBuilder importQueryBuilder;
    private final YamlLoader yamlLoader;

    public ServiceManager(ImportQueryBuilder importQueryBuilder, YamlLoader yamlLoader) {
        this.importQueryBuilder = importQueryBuilder;
        this.yamlLoader = yamlLoader;
    }

    public JdbcTemplate createJdbcTemplate(Database database){
        HikariConfig config = new HikariConfig();

        config.setJdbcUrl(database.getHost());
        config.setUsername(database.getUsername());
        config.setPassword(database.getPassword());
        config.setDriverClassName(database.getDriver());

        DataSource dataSource = new HikariDataSource(config);
        return new JdbcTemplate(dataSource);
    }

}
