package com.csv.importer.csv.writer;

import com.univocity.parsers.csv.CsvWriter;
import com.univocity.parsers.csv.CsvWriterSettings;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public abstract class InvalidCsvWriter {

    public abstract Resource writeCsv(List<String[]> records);

    protected Resource writeCsv(String[] headers,List<String[]> records){
        CsvWriterSettings settings = new CsvWriterSettings();
        settings.setHeaders(headers);

        try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream()){
            CsvWriter csvWriter = new CsvWriter(outputStream, settings);
            csvWriter.writeHeaders();
            for (String[] row: records){
                csvWriter.writeRow(row);
            }
            csvWriter.flush();
            return new ByteArrayResource(outputStream.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
