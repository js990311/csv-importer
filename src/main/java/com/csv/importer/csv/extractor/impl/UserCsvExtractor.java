package com.csv.importer.csv.extractor.impl;

import com.csv.importer.csv.extractor.AbstractCsvExtractor;
import com.csv.importer.csv.type.CsvEntityType;
import com.univocity.parsers.csv.CsvParser;
import org.springframework.stereotype.Component;

@Component
public class UserCsvExtractor extends AbstractCsvExtractor {

    @Override
    public boolean isSupport(CsvEntityType type) {
        return type == CsvEntityType.User;
    }

    @Override
    public boolean isValid(String[] row) {
        if(!isBlank(row[0])){
            return false;
        }
        if(!isBlank(row[1]) || !row[1].contains("@")){
            return false;
        }
        if(!isPositiveInteger(row[2])){
            return false;
        }
        return true;
    }
}
