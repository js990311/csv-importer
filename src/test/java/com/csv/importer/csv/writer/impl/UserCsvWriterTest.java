package com.csv.importer.csv.writer.impl;

import com.csv.importer.csv.dto.CsvExtractResult;
import com.csv.importer.csv.extractor.impl.UserCsvExtractor;
import com.csv.importer.csv.file.access.impl.LocalFileSystemAccessObject;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.Resource;

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
        Resource resource = csvWriter.writeCsv(extract.getInValidRecords());
        fileSAO.save("asdf-invalid.csv", resource);
    }
}