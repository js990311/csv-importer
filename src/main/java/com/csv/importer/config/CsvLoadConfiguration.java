package com.csv.importer.config;

import com.rejs.csvloader.CsvLoadCommandLineRunner;
import com.rejs.csvloader.config.AbstractCsvLoadConfiguration;
import com.rejs.csvloader.config.configurer.CsvColumnValidateServiceConfigurer;
import com.rejs.csvloader.config.configurer.CsvLoadBuilder;
import com.rejs.csvloader.config.configurer.JdbcBatchInsertRepositoryConfigurer;
import com.rejs.csvloader.file.LocalFileSystemAccessObject;
import com.rejs.csvloader.validator.impl.NotBlankValidator;
import com.rejs.csvloader.yaml.ImportPropertiesLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("loadcsv")
@Configuration
public class CsvLoadConfiguration extends AbstractCsvLoadConfiguration {

    @Override
    public CsvLoadBuilder csvLoadBuilder(CsvLoadBuilder builder) {
        return builder
                .jdbcBatchInsertRepository((new JdbcBatchInsertRepositoryConfigurer()).withDefault())
                .csvColumnValidateService((new CsvColumnValidateServiceConfigurer()).withDefault()
                        .custom(
                                new NotBlankValidator()
                        ));
    }

    @Bean
    public CsvLoadCommandLineRunner csvLoadCommandLineRunner(){
        return new CsvLoadCommandLineRunner(csvLoadService(), new ImportPropertiesLoader(new LocalFileSystemAccessObject()));
    }
}
