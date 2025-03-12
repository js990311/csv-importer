package com.csv.importer.yaml.service.validation;

import com.csv.importer.yaml.dto.Column;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Component
public class ValidationManager {
    private final List<CsvDataValidation> validations;

    public ValidationManager(List<CsvDataValidation> validations) {
        this.validations = validations;
    }

    public CsvParser getCsvParser(){
        CsvParserSettings settings = new CsvParserSettings();
        settings.setHeaderExtractionEnabled(true);
        return new CsvParser(settings);
    }

    public DataResult extractCsv(List<Column> columns, Resource resource){
        CsvParser parser = getCsvParser();
        DataResult ret = new DataResult();
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))){
            for(String[] row : parser.iterate(reader)){
                try { // 현재 행이 추출되고 있는가?
                    int idx = 0;
                    Object[] objects = new Object[columns.size()];
                    for(Column column : columns){
                        String type = column.getType();
                        if(column.getValidationType() != null){
                            type = column.getValidationType();
                        }
                        objects[idx] = extract(row[column.getCsvIndex()], type);
                    }
                    ret.addValidRecords(objects);
                }catch (CsvInValidationException ex){
                    // 현재 행을 추출하는 데 실패함
                    ret.addInValidRecords(row);
                }catch (RuntimeException ex){
                    throw new RuntimeException(ex);
                }
            }
        } catch (IOException e) {
            // 아예 CSV 파일을 import하는데 실패함
            throw new RuntimeException(e);
        }
        return ret;
    }

    private Object extract(String s, String type) {
        for (CsvDataValidation validator : validations){
            if(validator.support(type)){
                return validator.isValid(s);
            }
        }
        throw new CsvInValidationException();
    }
}
