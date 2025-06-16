package com.csv.importer.domain.workfile.dto;

import com.csv.importer.domain.workfile.entity.WorkFile;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class WorkFileDto {
    private String originalFileName;
    private String storedFileName;
    private LocalDateTime uploadTime;

    public WorkFileDto(String originalFileName, String storedFileName, LocalDateTime uploadTime) {
        this.originalFileName = originalFileName;
        this.storedFileName = storedFileName;
        this.uploadTime = uploadTime;
    }

    public static WorkFileDto of(WorkFile workFile){
        return new WorkFileDto(workFile.getOriginalFileName(), workFile.getStoredFileName(), workFile.getUploadTime());
    }
}
