package com.csv.importer.domain.csvfile.dto;

import com.csv.importer.domain.csvfile.entity.CsvFile;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CsvFileDto {
    private String originalFileName;
    private String storedFileName;
    private LocalDateTime uploadTime;

    public CsvFileDto(String originalFileName, String storedFileName, LocalDateTime uploadTime) {
        this.originalFileName = originalFileName;
        this.storedFileName = storedFileName;
        this.uploadTime = uploadTime;
    }

    public static CsvFileDto of(CsvFile csvFile){
        return new CsvFileDto(csvFile.getOriginalFileName(), csvFile.getStoredFileName(), csvFile.getUploadTime());
    }
}
