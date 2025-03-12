package com.csv.importer.yaml.service;

import com.csv.importer.csv.file.access.FileSystemAccessObject;
import com.csv.importer.yaml.dto.Column;
import com.csv.importer.yaml.dto.Database;
import com.csv.importer.yaml.dto.Root;
import com.csv.importer.yaml.dto.Work;
import com.csv.importer.yaml.service.insert.BatchInsertService;
import com.csv.importer.yaml.service.validation.DataResult;
import com.csv.importer.yaml.service.validation.ValidationManager;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
@Component
public class ServiceManager {
    private final ImportQueryBuilder importQueryBuilder;
    private final YamlLoader yamlLoader;
    private final FileSystemAccessObject fileSAO;
    private final ValidationManager validationManager;
    private final BatchInsertService batchInsertServicea;


    public JdbcTemplate createJdbcTemplate(Database database){
        HikariConfig config = new HikariConfig();

        config.setJdbcUrl(database.getHost());
        config.setUsername(database.getUsername());
        config.setPassword(database.getPassword());
        config.setDriverClassName(database.getDriver());

        DataSource dataSource = new HikariDataSource(config);
        return new JdbcTemplate(dataSource);
    }

    @Transactional
    public void execute(String configPath, String csvPath){
        Root config = yamlLoader.loadYaml(configPath);
        JdbcTemplate jdbcTemplate = createJdbcTemplate(config.getDatabase());
        Resource resource = fileSAO.load(csvPath);
        for(Work work: config.getWorks().getWork()){
            // insert Query 작성
            String insertQuery = importQueryBuilder.importSql(work);

            // 데이터 추출
            List<Column> columns = work.getColumns();
            DataResult dataResult = validationManager.extractCsv(columns, resource);

            // 데이터 삽입
            List<Object[]> validRecords = dataResult.getValidRecords();
            batchInsertServicea.batchInsert(jdbcTemplate, insertQuery, validRecords, columns);

            // TODO InvalidRecord 작성 후 기록 남기기
        }
    }
}
