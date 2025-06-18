package com.csv.importer.domain.workfile.dto;

import com.csv.importer.domain.workfile.entity.WorkFile;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class WorkFileDto {
    private Long id;
    private String originalFileName;
    private String storedFileName;
    private LocalDateTime uploadTime;
    private Long workspaceId;

    public WorkFileDto(Long id, String originalFileName, String storedFileName, LocalDateTime uploadTime, Long workspaceId) {
        this.id = id;
        this.originalFileName = originalFileName;
        this.storedFileName = storedFileName;
        this.uploadTime = uploadTime;
        this.workspaceId = workspaceId;
    }

    public static WorkFileDto of(WorkFile workFile){
        return new WorkFileDto(workFile.getId(), workFile.getOriginalFileName(), workFile.getStoredFileName(), workFile.getUploadTime(), workFile.getWorkspaceId());
    }
}
