package com.csv.importer.yaml.service.validation;

import com.csv.importer.csv.file.access.FileSystemAccessObject;
import com.csv.importer.yaml.dto.Root;
import com.csv.importer.yaml.service.YamlLoader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ValidationManagerTest {

    @Autowired private ValidationManager validationManager;
    @Autowired private YamlLoader yamlLoader;
    @Autowired private FileSystemAccessObject fileSystemAccessObject;

    @Test
    void extractCsv() {
        Root config = yamlLoader.loadYaml("test.yml");
        Resource data = fileSystemAccessObject.load("test.csv");

        DataResult dataResult = validationManager.extractCsv(config.getWorks().getWork().get(0).getColumns(), data);
        assertNotNull(dataResult);
        assertEquals(5, dataResult.getValidRecords().size());
        assertEquals(3, dataResult.getInValidRecords().size());
    }
}