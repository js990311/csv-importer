package com.csv.importer.csv.repository;

import com.csv.importer.csv.type.CsvEntityType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class CsvBatchInsertRepositoryManager {
    private final List<CsvBatchInsertRepository> batchInsertRepositories;

    @Transactional
    public void batchInsert(CsvEntityType type, List<String[]> rows){
        for(CsvBatchInsertRepository batchInsertRepository : batchInsertRepositories){
            if(batchInsertRepository.isSupport(type)){
                batchInsertRepository.batchInsert(rows);
                return;
            }
        }
        throw new RuntimeException();
    }
}
