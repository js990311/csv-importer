package com.csv.importer.csv.repository;

import com.csv.importer.csv.type.CsvEntityType;

import java.util.List;

public interface CsvBatchInsertRepository {
    void batchInsert(List<String[]> rows);
    boolean isSupport(CsvEntityType type);
}
