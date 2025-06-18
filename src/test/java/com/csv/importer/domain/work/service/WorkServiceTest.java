package com.csv.importer.domain.work.service;

import com.csv.importer.domain.csvfile.dto.CsvFileDto;
import com.csv.importer.domain.csvfile.service.CsvFileService;
import com.csv.importer.domain.workfile.dto.WorkFileDto;
import com.csv.importer.domain.workfile.service.WorkFileService;
import com.rejs.csvloader.CsvLoadService;
import com.rejs.csvloader.yaml.properties.config.ImportProperties;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class WorkServiceTest {
    @Autowired
    private WorkService workService;

    @MockitoBean
    private WorkFileService workFileService;
    @MockitoBean
    private CsvFileService csvFileService;

    @MockitoBean
    private CsvLoadService csvLoadService;

    @Test
    @DisplayName("실행되는지 확인")
    void loadCsv() {
        String propertyStoreName = "test.yml";
        String dataStoreName = "test.csv";

        when(workFileService.findById(any(Long.class))).thenReturn(new WorkFileDto(null, null, propertyStoreName, null, null));
        when(csvFileService.readById(any(Long.class))).thenReturn(new CsvFileDto(null, null, dataStoreName, null, null));


        workService.loadCsv(1L, 1L);

        verify(csvLoadService, times(1)).load(any(ImportProperties.class), any(Resource.class));
    }
}