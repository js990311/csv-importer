package com.csv.importer.domain.work.space.repository;

import com.csv.importer.domain.work.space.entity.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkspaceRepository extends JpaRepository<Workspace, Long> {
}
