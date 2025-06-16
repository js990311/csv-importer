package com.csv.importer.domain.workfile.service;

import com.csv.importer.domain.workfile.dto.WorkFileDto;
import com.csv.importer.domain.workfile.entity.WorkFile;
import com.csv.importer.domain.workfile.repository.WorkFileRepository;
import com.csv.importer.domain.workspace.entity.Workspace;
import com.csv.importer.domain.workspace.repository.WorkspaceRepository;
import com.rejs.csvloader.file.FileSystemAccessObject;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class WorkFileService {
    private final WorkFileRepository workFileRepository;

    @Qualifier("multipartFileLocalSystemAccessObject")
    private final FileSystemAccessObject fileSAO;

    private final WorkspaceRepository workspaceRepository;

    // CREATE
    @Transactional
    public WorkFileDto createWorkFile(Long workspaceId, MultipartFile file){
        String originalFilename = file.getOriginalFilename();
        String storedFileName = UUID.randomUUID().toString() + ".csv";
        WorkFile workFile = new WorkFile(originalFilename, storedFileName);
        Workspace workspace = workspaceRepository.findById(workspaceId).orElseThrow();
        fileSAO.save(storedFileName, file.getResource());
        workFile.mapWorkspace(workspace);
        workFile = workFileRepository.save(workFile);
        return WorkFileDto.of(workFile);
    }

    // READ
    public WorkFileDto findById(Long id){
        WorkFile workFile = workFileRepository.findById(id).orElseThrow();
        return WorkFileDto.of(workFile);
    }

    public List<WorkFileDto> findWorkByWorkspaceId(long workspaceId){
        return workFileRepository.findWorkByWorkspaceId(workspaceId).stream().map(WorkFileDto::of).toList();
    }

    // UPDATE

    // DELETE
    @Transactional
    public void deleteById(Long id){
        WorkFile workFile = workFileRepository.findById(id).orElseThrow();
        workFileRepository.delete(workFile);
    }
}
