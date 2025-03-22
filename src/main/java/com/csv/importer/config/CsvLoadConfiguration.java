package com.csv.importer.config;

import com.rejs.csvloader.config.AbstractCsvLoadCommandLineRunnerConfiguration;
import com.rejs.csvloader.config.configurer.CsvColumnValidateServiceConfigurer;
import com.rejs.csvloader.config.configurer.CsvLoadBuilder;
import com.rejs.csvloader.config.configurer.JdbcBatchInsertRepositoryConfigurer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("loadcsv")
@Configuration
public class CsvLoadConfiguration extends AbstractCsvLoadCommandLineRunnerConfiguration {

    @Override
    public CsvLoadBuilder csvLoadBuilder(CsvLoadBuilder builder) {
        return builder
                .jdbcBatchInsertRepository((new JdbcBatchInsertRepositoryConfigurer()).withDefault())
                .csvColumnValidateService((new CsvColumnValidateServiceConfigurer()).withDefault())
        ;
    }
}
