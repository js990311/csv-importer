package com.csv.importer.domain.csvfile.dto;

import com.csv.importer.domain.csvfile.entity.CsvFile;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CsvFileDto {
    private Long id;
    private String originalFileName;
    private String storedFileName;
    private LocalDateTime uploadTime;
    private Long workspaceId;

    public CsvFileDto(Long id, String originalFileName, String storedFileName, LocalDateTime uploadTime, Long workspaceId) {
        this.id = id;
        this.originalFileName = originalFileName;
        this.storedFileName = storedFileName;
        this.uploadTime = uploadTime;
        this.workspaceId = workspaceId;
    }

    public static CsvFileDto of(CsvFile csvFile){
        return new CsvFileDto(csvFile.getId(), csvFile.getOriginalFileName(), csvFile.getStoredFileName(), csvFile.getUploadTime(),csvFile.getWorkspaceId());
    }
}
