package com.csv.importer.yaml.service.validation.impl;

import com.csv.importer.yaml.service.validation.CsvDataValidation;
import com.csv.importer.yaml.service.validation.CsvInValidationException;
import org.springframework.stereotype.Component;

@Component
public class LongValidation implements CsvDataValidation {

    @Override
    public Object isValid(String col) {
        try {
            long number = Long.parseLong(col);
            return number;
        }catch (RuntimeException e){
            throw new CsvInValidationException();
        }
    }

    @Override
    public boolean support(String type) {
        return type.equals("LONG");
    }
}
