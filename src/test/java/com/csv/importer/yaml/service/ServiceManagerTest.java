package com.csv.importer.yaml.service;

import com.csv.importer.csv.file.access.FileSystemAccessObject;
import com.csv.importer.csv.file.access.impl.LocalFileSystemAccessObject;
import com.csv.importer.yaml.dto.Root;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
class ServiceManagerTest {
    @Autowired private YamlLoader yamlLoader;
    @Autowired private ServiceManager serviceManager;

    @Test
    void createJdbcTemplate() {
        Root root = yamlLoader.loadYaml("test.yml");
        JdbcTemplate jdbcTemplate = serviceManager.createJdbcTemplate(root.getDatabase());
        jdbcTemplate.execute("select * from test_users;");
    }

    @Test
    void execute() {
        serviceManager.execute("test.yml", "test.csv");
    }
}