package com.csv.importer.file.controller;

import com.csv.importer.file.dto.FilesDto;
import com.csv.importer.file.dto.ResourceDto;
import com.csv.importer.file.service.FilesService;
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
    public FilesDto uploadFile(@RequestParam("file") MultipartFile file){
        FilesDto filesDto = filesService.saveFile(file);
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
