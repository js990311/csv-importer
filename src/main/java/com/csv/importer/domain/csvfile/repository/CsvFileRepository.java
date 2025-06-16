package com.csv.importer.domain.csvfile.repository;

import com.csv.importer.domain.csvfile.entity.CsvFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CsvFileRepository extends JpaRepository<CsvFile, Long> {
    List<CsvFile> findByWorkspaceId(Long workspaceId);
}
