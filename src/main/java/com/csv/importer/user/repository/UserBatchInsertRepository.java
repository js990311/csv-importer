package com.csv.importer.user.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class UserBatchInsertRepository {
    private final JdbcTemplate jdbcTemplate;

    private final String BATCH_INSERT = "INSERT INTO users (name, email, age) VALUES (?,?,?)";

    @Transactional
    public void batchInsert(List<String[]> rows){
        jdbcTemplate.batchUpdate(BATCH_INSERT, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                String[] row = rows.get(i);
                ps.setString(1, row[0]);
                ps.setString(2, row[1]);
                ps.setInt(3, Integer.parseInt(row[2]));
            }

            @Override
            public int getBatchSize() {
                return rows.size();
            }
        });
    }
}
