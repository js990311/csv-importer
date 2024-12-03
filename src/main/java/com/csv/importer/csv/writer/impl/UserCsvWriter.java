package com.csv.importer.csv.writer.impl;

import com.csv.importer.csv.writer.InvalidCsvWriter;
import com.univocity.parsers.csv.CsvWriter;
import com.univocity.parsers.csv.CsvWriterSettings;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class UserCsvWriter extends InvalidCsvWriter {

    private String[] headers = new String[]{"name", "email", "age"};

    @Override
    public Resource writeCsv(List<String[]> records){
        return writeCsv(headers, records);
    }

}
