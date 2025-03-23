package com.csv.importer.domain.file.repository;

import com.csv.importer.domain.file.CsvFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CsvFileRepository extends JpaRepository<CsvFile, Long> {
}
