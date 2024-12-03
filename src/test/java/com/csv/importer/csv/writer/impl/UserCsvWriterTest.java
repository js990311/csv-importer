package com.csv.importer.csv.writer.impl;

import com.csv.importer.csv.dto.CsvExtractResult;
import com.csv.importer.csv.extractor.impl.UserCsvExtractor;
import com.csv.importer.csv.writer.InvalidCsvWriter;
import com.csv.importer.file.access.impl.LocalFileSystemAccessObject;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.Resource;

import static org.junit.jupiter.api.Assertions.*;

class UserCsvWriterTest {
    private LocalFileSystemAccessObject fileSAO;
    private UserCsvExtractor csvExtractor;
    private UserCsvWriter csvWriter;

    public UserCsvWriterTest(){
        fileSAO = new LocalFileSystemAccessObject();
        csvExtractor = new UserCsvExtractor();
        this.csvWriter = new UserCsvWriter();
    }


    @Test
    void writeCsv() {
        Resource load = fileSAO.load("asdf.csv");
        CsvExtractResult extract = csvExtractor.extract(load);
        csvWriter.writeCsv(extract.getInValidRecords());
    }
}