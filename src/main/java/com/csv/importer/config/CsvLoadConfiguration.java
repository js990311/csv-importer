package com.csv.importer.config;

import com.csv.importer.domain.file.service.util.MultipartFileLocalSystemAccessObject;
import com.csv.importer.domain.file.service.util.MultipartFileSystemAccessObject;
import com.rejs.csvloader.config.AbstractCsvLoadCommandLineRunnerConfiguration;
import com.rejs.csvloader.config.configurer.CsvColumnValidateServiceConfigurer;
import com.rejs.csvloader.config.configurer.CsvLoadBuilder;
import com.rejs.csvloader.config.configurer.JdbcBatchInsertRepositoryConfigurer;
import com.rejs.csvloader.file.FileSystemAccessObject;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("loadcsv")
@RequiredArgsConstructor
@Configuration
public class CsvLoadConfiguration extends AbstractCsvLoadCommandLineRunnerConfiguration {

    private final MultipartFileSystemAccessObject fileSystemAccessObject;

    @Override
    public CsvLoadBuilder csvLoadBuilder(CsvLoadBuilder builder) {
        return builder
                .jdbcBatchInsertRepository((new JdbcBatchInsertRepositoryConfigurer()).withDefault())
                .csvColumnValidateService((new CsvColumnValidateServiceConfigurer()).withDefault())
        ;
    }

    @Override
    public FileSystemAccessObject fileSystemAccessObject() {
        return fileSystemAccessObject;
    }
}
