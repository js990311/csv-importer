package com.csv.importer.yaml.service.validation;

import lombok.Getter;

@Getter
public class CsvInValidationException extends RuntimeException {
    public CsvInValidationException() {
    }
}
