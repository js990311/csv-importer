package com.csv.importer.csv.file.dto;

import com.csv.importer.csv.file.entity.Files;
import lombok.Getter;

@Getter
public class FilesDto {
    private String originalFileName;
    private String storePath;

    public FilesDto(String originalFileName, String storePath) {
        this.originalFileName = originalFileName;
        this.storePath = storePath;
    }

    public static FilesDto from(Files files){
        return new FilesDto(files.getOriginalFileName(), files.getStorePath());
    }
}
