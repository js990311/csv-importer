package com.csv.importer.yaml.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class Work {
    private String tableName;
    private List<Column> columns;
}
