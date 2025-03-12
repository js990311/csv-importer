package com.csv.importer.yaml.service;

import com.csv.importer.csv.file.access.FileSystemAccessObject;
import com.csv.importer.csv.file.access.impl.LocalFileSystemAccessObject;
import com.csv.importer.yaml.dto.Root;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.*;

class ServiceManagerTest {
    private YamlLoader yamlLoader;
    private ImportQueryBuilder importQueryBuilder;
    private FileSystemAccessObject fileSystemAccessObject;
    private ServiceManager serviceManager;

    public ServiceManagerTest() {
        fileSystemAccessObject = new LocalFileSystemAccessObject();
        yamlLoader = new YamlLoader(fileSystemAccessObject);
        importQueryBuilder = new ImportQueryBuilder();
        serviceManager = new ServiceManager(importQueryBuilder, yamlLoader, fileSystemAccessObject);
    }

    @Test
    void createJdbcTemplate() {
        Root root = yamlLoader.loadYaml("test.yml");
        JdbcTemplate jdbcTemplate = serviceManager.createJdbcTemplate(root.getDatabase());
        jdbcTemplate.execute("select * from test_users;");
    }
}