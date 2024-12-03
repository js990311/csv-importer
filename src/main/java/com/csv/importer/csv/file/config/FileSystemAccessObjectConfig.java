package com.csv.importer.csv.file.config;

import com.csv.importer.csv.file.access.FileSystemAccessObject;
import com.csv.importer.csv.file.access.impl.LocalFileSystemAccessObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileSystemAccessObjectConfig {
    @Bean
    public FileSystemAccessObject fileSystemAccessObject(){
        return new LocalFileSystemAccessObject();
    }
}
