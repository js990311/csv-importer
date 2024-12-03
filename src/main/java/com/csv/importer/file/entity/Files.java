package com.csv.importer.file.entity;

import com.csv.importer.csv.type.CsvEntityType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Entity
public class Files {
    @Id
    @GeneratedValue
    private Long id;

    private String originalFileName;

    private String fileStoreName;

    private String fileExtension;

    @Enumerated(EnumType.STRING)
    private CsvEntityType type;

    public String getStorePath(){
        return fileStoreName + "." + fileExtension;
    }

    private static String extractExtension(String filename){
        int dotIdx = filename.lastIndexOf('.');
        return filename.substring(dotIdx+1);
    }

    public Files(CsvEntityType type, String originalFileName) {
        this.type = type;
        this.originalFileName = originalFileName;
        this.fileExtension = extractExtension(originalFileName);
        this.fileStoreName = UUID.randomUUID().toString();
    }

}
