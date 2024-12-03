package com.csv.importer.csv.file.controller;

import com.csv.importer.csv.file.dto.FilesDto;
import com.csv.importer.csv.file.dto.ResourceDto;
import com.csv.importer.csv.file.service.FilesService;
import com.csv.importer.csv.type.CsvEntityType;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("/files")
public class FilesController {
    private final FilesService filesService;

    @PostMapping("/upload")
    public FilesDto uploadFile(
            @RequestParam("type") CsvEntityType type,
            @RequestParam("file") MultipartFile file){
        FilesDto filesDto = filesService.saveFile(type,file);
        return filesDto;
    }

    @GetMapping("/{fileStoreName}")
    public ResponseEntity<Resource> downloadByFilename(@PathVariable("fileStoreName") String filename){
        ResourceDto resource = filesService.loadByFilename(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, resource.getHeader())
                .body(resource.getResource());
    }
}
