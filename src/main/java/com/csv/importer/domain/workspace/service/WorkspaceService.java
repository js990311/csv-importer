package com.csv.importer.domain.workspace.service;

import com.csv.importer.domain.workspace.dto.WorkspaceDto;
import com.csv.importer.domain.workfile.service.WorkFileService;
import com.csv.importer.domain.workspace.entity.Workspace;
import com.csv.importer.domain.workspace.repository.WorkspaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class WorkspaceService {
    private final WorkspaceRepository workspaceRepository;
    private final WorkFileService workFileService;

    /* Create */
    @Transactional
    public WorkspaceDto createWorkspace(String name){
        Workspace workspace = new Workspace(name);
        workspace = workspaceRepository.save(workspace);
        return WorkspaceDto.of(workspace);
    }

    /* Read */
    public WorkspaceDto readById(Long id){
        Workspace workspace = workspaceRepository.findById(id).orElseThrow();
        return WorkspaceDto.of(workspace);
    }

    /* Update */

    /* Delete */
}
