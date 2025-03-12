package com.csv.importer.yaml.service.insert;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface PreparedStatementSetter {
    public void set(PreparedStatement ps, int index, Object o) throws SQLException;
    public boolean isSupport(String type);
}
