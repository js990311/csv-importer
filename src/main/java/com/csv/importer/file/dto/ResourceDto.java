package com.csv.importer.file.dto;

import lombok.Getter;
import org.springframework.core.io.Resource;

@Getter
public class ResourceDto {
    private Resource resource;
    private String header;

    public ResourceDto(Resource resource, String filename){
        this.resource = resource;
        this.header = "attachment;" + "filename=\"" + filename + "\"";
    }
}
