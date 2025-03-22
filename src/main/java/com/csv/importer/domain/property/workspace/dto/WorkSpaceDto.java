package com.csv.importer.domain.property.workspace.dto;

import com.csv.importer.domain.property.workspace.WorkSpace;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class WorkSpaceDto {
    private Long id;
    private String name;
    private String databaseUrl;
    private String username;
    private String password;
    private String driver;

    public WorkSpaceDto(WorkSpace workSpace) {
        this.id = workSpace.getId();
        this.name = workSpace.getName();
        this.databaseUrl = workSpace.getDatabaseUrl();
        this.username = workSpace.getUsername();
        this.password = workSpace.getPassword();
        this.driver = workSpace.getDriver();
    }

    public static WorkSpaceDto of(WorkSpace workSpace){
        return new WorkSpaceDto(workSpace);
    }
}
