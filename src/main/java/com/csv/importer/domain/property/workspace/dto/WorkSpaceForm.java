package com.csv.importer.domain.property.workspace.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class WorkSpaceForm {
    private String name;
    private String databaseUrl;
    private String username;
    private String password;
    private String driver;
}
