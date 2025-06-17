package com.csv.importer.domain.workspace.dto;

import com.csv.importer.domain.workspace.entity.Workspace;
import lombok.Getter;

@Getter
public class WorkspaceDto {
    private Long id;
    private String name;
    private int dataCounts;
    private int workCounts;

    public WorkspaceDto(Long id, String name, int dataCounts, int workCounts) {
        this.id = id;
        this.name = name;
        this.dataCounts = dataCounts;
        this.workCounts = workCounts;
    }

    public WorkspaceDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static WorkspaceDto of(Workspace workspace){
        return new WorkspaceDto(workspace.getId(), workspace.getName());
    }
}
