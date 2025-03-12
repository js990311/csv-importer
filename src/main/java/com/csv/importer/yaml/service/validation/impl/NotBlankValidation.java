package com.csv.importer.yaml.service.validation.impl;

import com.csv.importer.yaml.service.validation.CsvDataValidation;
import com.csv.importer.yaml.service.validation.CsvInValidationException;
import org.springframework.stereotype.Component;

@Component
public class NotBlankValidation implements CsvDataValidation {
    @Override
    public Object isValid(String col) {
        if(col != null && !col.isBlank()){
            return col;
        }else {
            throw new CsvInValidationException();
        }
    }

    @Override
    public boolean support(String type) {
        return type.equals("NOTBLANK");
    }
}
