package com.csv.importer.domain.work.service;

import com.csv.importer.domain.csvfile.dto.CsvFileDto;
import com.csv.importer.domain.csvfile.service.CsvFileService;
import com.csv.importer.domain.workfile.dto.WorkFileDto;
import com.csv.importer.domain.workfile.service.WorkFileService;
import com.rejs.csvloader.CsvLoadService;
import com.rejs.csvloader.file.FileSystemAccessObject;
import com.rejs.csvloader.yaml.ImportPropertiesLoader;
import com.rejs.csvloader.yaml.properties.config.ImportProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class WorkService {
    private final CsvLoadService csvLoadService;
    private final ImportPropertiesLoader propertiesLoader;
    private final FileSystemAccessObject fileSystemAccessObject;
    private final WorkFileService workFileService;
    private final CsvFileService csvFileService;

    public void loadCsv(Long workId, Long dataId){
        WorkFileDto work = workFileService.findById(workId);
        CsvFileDto data = csvFileService.readById(dataId);
        ImportProperties properties = propertiesLoader.loadProperties(work.getStoredFileName());
        Resource load = fileSystemAccessObject.load(data.getStoredFileName());
        csvLoadService.load(properties, load);
    }
}
