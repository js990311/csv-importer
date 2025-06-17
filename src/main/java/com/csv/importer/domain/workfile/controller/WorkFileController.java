package com.csv.importer.domain.workfile.controller;

import com.csv.importer.domain.workfile.dto.WorkFileDto;
import com.csv.importer.domain.workfile.service.WorkFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class WorkFileController {
    private final WorkFileService workFileService;

    @GetMapping("/api/workspace/{workspaceId}/works")
    public List<WorkFileDto> getWorks(@PathVariable("workspaceId") long workspaceId){
        return workFileService.findWorkByWorkspaceId(workspaceId);
    }

    @PostMapping("/api/workspace/{workspaceId}/works")
    public ResponseEntity<WorkFileDto> postWorkfile(@PathVariable("workspaceId") long workspaceId, @RequestParam("file") MultipartFile file){
        WorkFileDto workFile = workFileService.createWorkFile(workspaceId, file);
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/works/{id}")
                .buildAndExpand(workFile.getId())
                .toUri();
        return ResponseEntity.created(location).body(workFile);
    }

    @GetMapping("/api/works/{workId}")
    public WorkFileDto getWorkfile(@PathVariable("workId") long workId){
        return workFileService.findById(workId);
    }
}
