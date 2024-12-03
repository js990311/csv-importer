package com.csv.importer.book.repository.csv;

import com.csv.importer.csv.extractor.AbstractCsvExtractor;
import com.csv.importer.csv.type.CsvEntityType;
import org.springframework.stereotype.Component;

@Component
public class BookCsvExtractor extends AbstractCsvExtractor {
    @Override
    public boolean isSupport(CsvEntityType type) {
        return type == CsvEntityType.Book;
    }

    @Override
    public boolean isValid(String[] row) {
        if(!isBlank(row[6]) || (row[6].length() > 255)){
            return false;
        }
        if(!isLong(row[7])){
            return false;
        }
        if(!isLong(row[23])){
            return false;
        }
        return true;
    }
}
