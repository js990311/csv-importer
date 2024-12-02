package com.csv.importer.file.config;

import com.csv.importer.file.FileSystemAccessObject;
import com.csv.importer.file.impl.LocalFileSystemAccessObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileSystemAccessObjectConfig {
    @Bean
    public FileSystemAccessObject fileSystemAccessObject(){
        return new LocalFileSystemAccessObject();
    }
}
