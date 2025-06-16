package com.csv.importer.domain.work.config;

import com.csv.importer.utils.file.MultipartFileLocalSystemAccessObject;
import com.csv.importer.utils.file.MultipartFileSystemAccessObject;
import com.rejs.csvloader.config.AbstractCsvLoadConfiguration;
import com.rejs.csvloader.file.FileSystemAccessObject;
import com.rejs.csvloader.file.LocalFileSystemAccessObject;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class CsvLoadConfig extends AbstractCsvLoadConfiguration {

    @Override
    public FileSystemAccessObject fileSystemAccessObject() {
        return new MultipartFileLocalSystemAccessObject();
    }
}
