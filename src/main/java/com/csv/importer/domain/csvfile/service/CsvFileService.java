package com.csv.importer.domain.csvfile.service;

import com.csv.importer.domain.csvfile.entity.CsvFile;
import com.csv.importer.domain.csvfile.dto.CsvFileDto;
import com.csv.importer.domain.csvfile.repository.CsvFileRepository;
import com.csv.importer.domain.workspace.entity.Workspace;
import com.csv.importer.domain.workspace.repository.WorkspaceRepository;
import com.csv.importer.utils.file.FileExtensionUtils;
import com.csv.importer.utils.file.MultipartFileSystemAccessObject;
import com.rejs.csvloader.file.FileSystemAccessObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CsvFileService {
    private final CsvFileRepository csvFileRepository;
    private final FileSystemAccessObject fileSAO;
    private final WorkspaceRepository workspaceRepository;

    /* Create */
    @Transactional
    public CsvFileDto
    createCsvFile(Long workspaceId, MultipartFile file){
        if(!FileExtensionUtils.isCsvFile(file)){
            throw new IllegalArgumentException();
        }
        String originalFilename = file.getOriginalFilename();
        String storedFileName = UUID.randomUUID().toString() + ".csv";
        CsvFile csvFile = new CsvFile(originalFilename, storedFileName);
        Workspace workspace = workspaceRepository.findById(workspaceId).orElseThrow();
        csvFile.mapWorkspace(workspace);
        fileSAO.save(storedFileName, file.getResource());
        csvFile = csvFileRepository.save(csvFile);
        return CsvFileDto.of(csvFile);
    }

    /* Read */
    public CsvFileDto readById(Long csvFileId){
        return CsvFileDto.of(csvFileRepository.findById(csvFileId).orElseThrow());
    }

    public List<CsvFileDto> readByWorkspaceId(Long workspaceId){
        return csvFileRepository.findByWorkspaceId(workspaceId).stream().map(CsvFileDto::of).toList();
    }
}
