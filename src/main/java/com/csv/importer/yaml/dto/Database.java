package com.csv.importer.yaml.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Database {
    private String host;
    private String username;
    private String password;
    private String driver;
}
