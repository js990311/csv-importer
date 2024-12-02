package com.csv.importer.csv.impl;

import com.csv.importer.csv.AbstractCsvExtractor;
import com.univocity.parsers.csv.CsvParser;

public class UserCsvExtractor extends AbstractCsvExtractor {

    public UserCsvExtractor(CsvParser parser){
        super(parser);
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
