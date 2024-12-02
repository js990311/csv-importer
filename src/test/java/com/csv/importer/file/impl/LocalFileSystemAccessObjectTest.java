package com.csv.importer.file.impl;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.Resource;

import static org.junit.jupiter.api.Assertions.*;

class LocalFileSystemAccessObjectTest {

    private final LocalFileSystemAccessObject fileSAO = new LocalFileSystemAccessObject();


    @Test
    void load() {
        Resource csvFile = fileSAO.load("asdf.csv");
        assertNotNull(csvFile);
    }
}