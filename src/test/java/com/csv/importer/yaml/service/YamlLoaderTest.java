package com.csv.importer.yaml.service;

import com.csv.importer.csv.file.access.impl.LocalFileSystemAccessObject;
import com.csv.importer.yaml.dto.Root;
import com.csv.importer.yaml.dto.Work;
import com.csv.importer.yaml.dto.Works;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class YamlLoaderTest {

    private YamlLoader yamlLoader;

    public YamlLoaderTest() {
        yamlLoader = new YamlLoader(new LocalFileSystemAccessObject());
    }

    @Test
    void loadYaml() {
        Root root = yamlLoader.loadYaml("test.yml");
        assertNotNull(root.getWorks());

        Works works = root.getWorks();
        assertNotNull(works.getWork());

        List<Work> workList = works.getWork();
        assertEquals(1, workList.size());

        Work work = workList.get(0);
        assertEquals("test_users", work.getTableName());
        assertEquals(4, work.getColumns().size());

        assertEquals("LONG", work.getColumns().get(0).getType());
        assertEquals(1, work.getColumns().get(1).getCsvIndex());
        assertEquals("age", work.getColumns().get(2).getName());
    }
}