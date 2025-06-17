package com.csv.importer.domain.csvfile.controller;

import com.csv.importer.domain.csvfile.dto.CsvFileDto;
import com.csv.importer.domain.csvfile.service.CsvFileService;
import com.csv.importer.domain.workfile.dto.WorkFileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class CsvFileController {
    private final CsvFileService csvFileService;

    @GetMapping("/api/workspaces/{workspaceId}/datas")
    public List<CsvFileDto> getDatas(@PathVariable("workspaceId") long workspaceId){
        return csvFileService.readByWorkspaceId(workspaceId);
    }

    @PostMapping("/api/workspaces/{workspaceId}/datas")
    public ResponseEntity<CsvFileDto> postData(@PathVariable("workspaceId") long workspaceId, @RequestParam("file") MultipartFile file){
        CsvFileDto csvFile = csvFileService.createCsvFile(workspaceId, file);
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/datas/{id}")
                .buildAndExpand(csvFile.getId())
                .toUri();
        return ResponseEntity.created(location).body(csvFile);
    }

    @GetMapping("/api/datas/{dataId}")
    public CsvFileDto getData(@PathVariable("dataId") long dataId){
        return csvFileService.readById(dataId);
    }

}
