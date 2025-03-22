package com.csv.importer.domain.property.workspace.work.repository;

import com.csv.importer.domain.property.workspace.work.Work;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkRepository extends JpaRepository<Work, Long> {
}
