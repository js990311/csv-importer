package com.csv.importer.domain.property.work.columns.repository;

import com.csv.importer.domain.property.work.columns.WorkColumn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkColumnRepository extends JpaRepository<WorkColumn, Long> {
}
