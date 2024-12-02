package com.csv.importer.csv.impl;

import com.csv.importer.csv.AbstractCsvExtractor;
import com.csv.importer.csv.dto.CsvExtractResult;
import com.univocity.parsers.csv.CsvParser;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserCsvExtractor extends AbstractCsvExtractor {

    public UserCsvExtractor(CsvParser parser){
        super(parser);
    }

    @Override
    public boolean isValid(String[] row) {
        String name = row[0];
        String email = row[1];
        String age = row[2];
        if(name == null || name.isBlank()){
            return false;
        }
        if(email == null || !email.contains("@")){
            return false;
        }
        try{
            int ageNumber = Integer.parseInt(age);
            if(ageNumber<0){
                return false;
            }
        }catch (NumberFormatException e){
            return false;
        }
        return true;
    }
}
