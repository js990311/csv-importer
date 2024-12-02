package com.csv.importer.file.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@Entity
public class Files {
    @Id
    @GeneratedValue
    private Long id;

    private String originalFileName;

    private String fileStoreName;

    private String fileExtension;

    public String getStorePath(){
        return fileStoreName + "." + fileExtension;
    }

    private String extractExtension(String filename){
        int dotIdx = filename.lastIndexOf('.');
        return filename.substring(dotIdx+1);
    }

    public Files(String originalFileName) {
        this.originalFileName = originalFileName;
        this.fileExtension = extractExtension(originalFileName);
        this.fileStoreName = UUID.randomUUID().toString();
    }
}
