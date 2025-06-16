package com.csv.importer.domain.csvfile.service;

import com.csv.importer.domain.csvfile.entity.CsvFile;
import com.csv.importer.domain.csvfile.dto.CsvFileDto;
import com.csv.importer.domain.csvfile.repository.CsvFileRepository;
import com.csv.importer.domain.workspace.entity.Workspace;
import com.csv.importer.domain.workspace.repository.WorkspaceRepository;
import com.csv.importer.utils.file.MultipartFileSystemAccessObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CsvFileService {
    private final CsvFileRepository csvFileRepository;
    private final MultipartFileSystemAccessObject fileSAO;
    private final WorkspaceRepository workspaceRepository;

    /* Create */
    @Transactional
    public CsvFileDto createCsvFile(Long workspaceId, MultipartFile file){
        String originalFilename = file.getOriginalFilename();
        String storedFileName = UUID.randomUUID().toString() + ".csv";
        CsvFile csvFile = new CsvFile(originalFilename, storedFileName);
        Workspace workspace = workspaceRepository.findById(workspaceId).orElseThrow();
        csvFile.mapWorkspace(workspace);
        fileSAO.save(storedFileName, file);
        csvFile = csvFileRepository.save(csvFile);
        return CsvFileDto.of(csvFile);
    }
}
