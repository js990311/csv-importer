package com.csv.importer.domain.workspace.repository;

import com.csv.importer.domain.workspace.dto.WorkspaceDto;
import com.csv.importer.domain.workspace.entity.Workspace;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WorkspaceRepository extends JpaRepository<Workspace, Long> {

    @Query("select new com.csv.importer.domain.workspace.dto.WorkspaceDto(ws.id, ws.name, size(ws.csvFiles), size(ws.workFiles)) from Workspace ws")
    Page<WorkspaceDto> findAllWithCounts(Pageable pageable);

    @Query("select new com.csv.importer.domain.workspace.dto.WorkspaceDto(ws.id, ws.name, size(ws.csvFiles), size(ws.workFiles)) from Workspace ws where ws.id = :workspaceId")
    WorkspaceDto findAllWithCountsById(@Param("workspaceId") long worksapceId);

}
