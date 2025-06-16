package com.csv.importer.domain.work.space.service;

import com.csv.importer.domain.work.dto.WorkspaceDto;
import com.csv.importer.domain.work.file.dto.WorkFileDto;
import com.csv.importer.domain.work.file.service.WorkFileService;
import com.csv.importer.domain.work.space.entity.Workspace;
import com.csv.importer.domain.work.space.repository.WorkspaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class WorkspaceService {
    private final WorkspaceRepository workspaceRepository;
    private final WorkFileService workFileService;

    /* Create */
    @Transactional
    public void createWorkspace(String name){
        Workspace workspace = new Workspace(name);
        workspaceRepository.save(workspace);
    }

    /* Read */
    public WorkspaceDto readById(Long id){
        Workspace workspace = workspaceRepository.findById(id).orElseThrow();
        return WorkspaceDto.of(workspace);
    }

    /* Update */

    /* Delete */
}
