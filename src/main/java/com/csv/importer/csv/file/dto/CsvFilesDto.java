package com.csv.importer.csv.file.dto;

import com.csv.importer.csv.file.entity.Files;
import lombok.Getter;
import org.springframework.core.io.Resource;

@Getter
public class CsvFilesDto {
    private Files files;
    private Resource resource;

    public CsvFilesDto(Files files, Resource resource) {
        this.files = files;
        this.resource = resource;
    }
}
