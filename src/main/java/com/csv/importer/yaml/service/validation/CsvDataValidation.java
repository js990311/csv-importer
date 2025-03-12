package com.csv.importer.yaml.service.validation;

public interface CsvDataValidation {
    Object isValid(String col);

    boolean support(String type);
}
