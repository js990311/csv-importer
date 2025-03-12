package com.csv.importer.yaml.service.validation.impl;

import com.csv.importer.yaml.service.validation.CsvDataValidation;
import com.csv.importer.yaml.service.validation.CsvInValidationException;
import org.springframework.stereotype.Component;

@Component
public class AgeValidation implements CsvDataValidation {
    @Override
    public Object isValid(String col) {
        try{
            int number = Integer.parseInt(col);
            if(number<0){
                throw new NumberFormatException();
            }else {
                return number;
            }
        }catch (NumberFormatException e){
            throw new CsvInValidationException();
        }
    }

    @Override
    public boolean support(String type) {
        return type.equals("AGE");
    }
}
