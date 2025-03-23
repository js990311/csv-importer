package com.csv.importer.domain.property.controller.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class WorkSpaceForm {
    private String name;
    private String databaseUrl;
    private String username;
    private String password;
    private String driver;

    private List<WorkForm> works;
}
