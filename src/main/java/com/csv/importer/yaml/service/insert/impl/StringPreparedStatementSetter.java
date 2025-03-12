package com.csv.importer.yaml.service.insert.impl;

import com.csv.importer.yaml.service.insert.PreparedStatementSetter;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class StringPreparedStatementSetter implements PreparedStatementSetter
{
    @Override
    public void set(PreparedStatement ps, int index, Object o) throws SQLException {
        ps.setString(index, (String) o);
    }

    @Override
    public boolean isSupport(String type) {
        return type.equals("STRING");
    }
}
