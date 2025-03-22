package com.csv.importer.domain.property.workspace.repository;

import com.csv.importer.domain.property.workspace.WorkSpace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkSpaceRepository extends JpaRepository<WorkSpace, Long> {
}
