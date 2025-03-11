package com.csv.importer.yaml.service;

import com.csv.importer.csv.file.access.impl.LocalFileSystemAccessObject;
import com.csv.importer.yaml.dto.Root;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImportQueryBuilderTest {

    private YamlLoader yamlLoader;
    private ImportQueryBuilder importQueryBuilder;

    public ImportQueryBuilderTest() {
        yamlLoader = new YamlLoader(new LocalFileSystemAccessObject());
        importQueryBuilder = new ImportQueryBuilder();
    }


    @Test
    void importSql() {
        Root root = yamlLoader.loadYaml("test.yml");
        String query = importQueryBuilder.importSql(root.getWorks().getWork().get(0));
        assertNotEquals("INSERT test_users(name, email, age, test_korean) VALUES (?, ?, ?, ?);", query);
    }
}