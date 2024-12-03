package com.csv.importer.csv.impl;

import com.csv.importer.csv.dto.CsvExtractResult;
import com.csv.importer.csv.extractor.impl.UserCsvExtractor;
import com.csv.importer.csv.file.access.impl.LocalFileSystemAccessObject;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.Resource;

import static org.junit.jupiter.api.Assertions.*;

class UserCsvExtractorTest {

    private LocalFileSystemAccessObject fileSAO;
    private UserCsvExtractor csvExtractor;

    public UserCsvExtractorTest(){
        fileSAO = new LocalFileSystemAccessObject();
        csvExtractor = new UserCsvExtractor();
    }

    @Test
    void testAsdf() {
        Resource resource = fileSAO.load("asdf.csv");
        CsvExtractResult result = csvExtractor.extract(resource);
        assertEquals(3, result.getInValidRecords().size());
        assertEquals(5, result.getValidRecords().size());
    }
}