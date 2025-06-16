package com.csv.importer.domain.work.dto;

import com.csv.importer.domain.work.space.entity.Workspace;
import lombok.Getter;

@Getter
public class WorkspaceDto {
    private Long id;
    private String name;

    public WorkspaceDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static WorkspaceDto of(Workspace workspace){
        return new WorkspaceDto(workspace.getId(), workspace.getName());
    }
}
