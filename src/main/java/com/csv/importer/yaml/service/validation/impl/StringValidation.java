package com.csv.importer.yaml.service.validation.impl;

import com.csv.importer.yaml.service.validation.CsvDataValidation;
import org.springframework.stereotype.Component;

@Component
public class StringValidation implements CsvDataValidation {
    @Override
    public Object isValid(String col) {
        return col;
    }

    @Override
    public boolean support(String type) {
        return type.equals("STRING");
    }
}
