package com.csv.importer.csv;

import com.csv.importer.csv.dto.CsvExtractResult;
import com.univocity.parsers.csv.CsvParser;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@RequiredArgsConstructor
public abstract class AbstractCsvExtractor {
    private final CsvParser parser;

    public CsvExtractResult extract(Resource resource){
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))){
            CsvExtractResult ret = new CsvExtractResult();
            for(String[] row : parser.iterate(reader)){
                if(isValid(row)){
                    ret.addValidRecords(row);
                }else {
                    ret.addInValidRecords(row);
                }
            }
            return ret;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public abstract boolean isValid(String[] row);

}
