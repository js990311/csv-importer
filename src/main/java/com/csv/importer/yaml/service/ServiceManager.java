package com.csv.importer.yaml.service;

import com.csv.importer.csv.file.access.FileSystemAccessObject;
import com.csv.importer.yaml.dto.Database;
import com.csv.importer.yaml.dto.Root;
import com.csv.importer.yaml.dto.Work;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class ServiceManager {
    private final ImportQueryBuilder importQueryBuilder;
    private final YamlLoader yamlLoader;
    private final FileSystemAccessObject fileSAO;

    public ServiceManager(ImportQueryBuilder importQueryBuilder, YamlLoader yamlLoader, FileSystemAccessObject fileSAO) {
        this.importQueryBuilder = importQueryBuilder;
        this.yamlLoader = yamlLoader;
        this.fileSAO = fileSAO;
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

    public void execute(String configPath, String csvPath){
        Root config = yamlLoader.loadYaml(configPath);
        JdbcTemplate jdbcTemplate = createJdbcTemplate(config.getDatabase());
        fileSAO.load(csvPath);
        for(Work work: config.getWorks().getWork()){
            String importSql = importQueryBuilder.importSql(work);
        }
    }
}
