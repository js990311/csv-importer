package com.csv.importer.domain.workspace.controller.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CreateWorkspaceRequest {
    private String name;

    public CreateWorkspaceRequest(String name) {
        this.name = name;
    }
}
