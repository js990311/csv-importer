package com.csv.importer.domain.workfile.repository;

import com.csv.importer.domain.workfile.entity.WorkFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WorkFileRepository extends JpaRepository<WorkFile, Long> {
    List<WorkFile> findWorkByWorkspaceId(Long workspaceId);
}
