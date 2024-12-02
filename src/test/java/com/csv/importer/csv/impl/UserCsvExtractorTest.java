package com.csv.importer.csv.impl;

import com.csv.importer.csv.dto.CsvExtractResult;
import com.csv.importer.file.impl.LocalFileSystemAccessObject;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.Resource;

import static org.junit.jupiter.api.Assertions.*;

class UserCsvExtractorTest {

    private CsvParser csvParser;
    private LocalFileSystemAccessObject fileSAO;
    private UserCsvExtractor csvExtractor;

    public UserCsvExtractorTest(){
        CsvParserSettings settings = new CsvParserSettings();
        settings.setHeaderExtractionEnabled(true);
        csvParser = new CsvParser(settings);
        fileSAO = new LocalFileSystemAccessObject();
        csvExtractor = new UserCsvExtractor(csvParser);
    }

    @Test
    void testAsdf() {
        Resource resource = fileSAO.load("asdf.csv");
        CsvExtractResult result = csvExtractor.extract(resource);
        assertEquals(3, result.getInValidRecords().size());
        assertEquals(5, result.getValidRecords().size());
    }
}