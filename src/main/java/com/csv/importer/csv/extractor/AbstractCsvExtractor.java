package com.csv.importer.csv.extractor;

import com.csv.importer.csv.dto.CsvExtractResult;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@RequiredArgsConstructor
public abstract class AbstractCsvExtractor {
    public CsvParser getCsvParser(){
        CsvParserSettings settings = new CsvParserSettings();
        settings.setHeaderExtractionEnabled(true);
        return new CsvParser(settings);
    }

    public CsvExtractResult extract(Resource resource){
        CsvParser parser = getCsvParser();
        CsvExtractResult ret = new CsvExtractResult();
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))){
            for(String[] row : parser.iterate(reader)){
                if(isValid(row)){
                    ret.addValidRecords(row);
                }else {
                    ret.addInValidRecords(row);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ret;
    }

    public abstract boolean isValid(String[] row);

    protected boolean isBlank(String record){
        return record != null && !record.isBlank();
    }

    protected boolean isInteger(String record){
        try {
            int number = Integer.parseInt(record);
        }catch (NumberFormatException e){
            return false;
        }
        return true;
    }

    protected boolean isPositiveInteger(String record){
        try{
            int number = Integer.parseInt(record);
            if(number<0){
                return false;
            }
        }catch (NumberFormatException e){
            return false;
        }
        return true;
    }

}
