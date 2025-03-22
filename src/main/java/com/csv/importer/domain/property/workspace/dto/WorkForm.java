package com.csv.importer.domain.property.workspace.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class WorkForm {
    private String tableName;
    private List<WorkColumnForm> columns;
}
