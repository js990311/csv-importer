package com.csv.importer.domain.workspace.controller.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CreateWorkspaceRequest {
    private String name;

    public CreateWorkspaceRequest(String name) {
        this.name = name;
    }
}
