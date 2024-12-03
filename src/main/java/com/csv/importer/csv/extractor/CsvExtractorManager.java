package com.csv.importer.csv.extractor;

import com.csv.importer.csv.dto.CsvExtractResult;
import com.csv.importer.csv.type.CsvEntityType;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class CsvExtractorManager {
    private final List<AbstractCsvExtractor> csvExtractors;

    public CsvExtractResult extract(CsvEntityType type, Resource resource) {
        for(AbstractCsvExtractor csvExtractor : csvExtractors){
            if(csvExtractor.isSupport(type)){
                return csvExtractor.extract(resource);
            }
        }
        return null;
    }
}
