package com.csv.importer.yaml.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Column {
    private String name;
    private String type;
    private Integer csvIndex;
    private String validationType;
}
