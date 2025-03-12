package com.csv.importer.yaml.service.insert;

import com.csv.importer.yaml.dto.Column;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class BatchInsertService {

    private final List<PreparedStatementSetter> statementSetters;

    public void batchInsert(JdbcTemplate jdbcTemplate, String insertQuery, List<Object[]> datas, List<Column> columns){
        int batchSize = 256;
        for(int i=0;i< datas.size();i +=batchSize){
            int end = Math.min(i+ batchSize, datas.size());
            insert(jdbcTemplate, insertQuery, datas.subList(i, end), columns);
        }
    }

    protected void insert(JdbcTemplate jdbcTemplate, String insertQuery, List<Object[]> datas, List<Column> columns){
        jdbcTemplate.batchUpdate(insertQuery, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Object[] data = datas.get(i);
                int idx = 0;
                for(Column column : columns){
                    if(data[idx] == null){
                        ps.setString(idx+1, null);
                    }else {
                        String type = column.getType();
                        PreparedStatementSetter setter = getSetter(type);
                        setter.set(ps, idx+1, data[idx]);
                    }
                    idx++;
                }
            }

            @Override
            public int getBatchSize() {
                return datas.size();
            }
        });
    }


    public PreparedStatementSetter getSetter(String type){
        for(PreparedStatementSetter setter: statementSetters){
            if(setter.isSupport(type)){
                return setter;
            }
        }
        // TODO
        throw new RuntimeException();
    }

}
