package com.csv.importer.yaml.service.validation.impl;

import com.csv.importer.yaml.service.validation.CsvDataValidation;
import com.csv.importer.yaml.service.validation.CsvInValidationException;
import org.springframework.stereotype.Component;

@Component
public class IntegerValidation implements CsvDataValidation {
    @Override
    public Object isValid(String col) {
        try {
            int number = Integer.parseInt(col);
            return number;
        }catch (RuntimeException e){
            throw new CsvInValidationException();
        }
    }

    @Override
    public boolean support(String type) {
        return type.equals("INTEGER");
    }

}
